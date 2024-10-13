/*

		Copyright 2014 Krrishnaaaa

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at
	http://www.apache.org/licenses/LICENSE-2.0
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
*/

package com.pcsalt.utility

import java.io.BufferedWriter
import java.io.File
import java.io.FileInputStream
import java.io.FileWriter
import java.security.MessageDigest
import java.util.Date
import java.util.Locale

data class Files(val md5: String, val filePath: String)
data class DuplicateDetails(val md5: String, val filePath: Set<String>)

class ListFiles {
  private var counter = 0
  private var totalFilesCount = 0
  private var lastMessageLength = 0

  fun find(dirToSearch: List<String>) {

    val mFiles = mutableListOf<Files>()
    val duplicates = mutableMapOf<String, MutableSet<String>>() // Map for grouping duplicates
    val ignoreFolders = setOf(".git", "build", "node_modules", ".gradle", ".idea") // Specify folders to ignore
    val ignoreFiles = setOf(".DS_Store")

    println("\nSearching for duplicate files in directories: ${dirToSearch.joinToString(", ")}")
    println("Processing started at " + Date())
    for (rootPath in dirToSearch) {
      countFiles(File(rootPath), ignoreFolders, ignoreFiles)
    }

    println("Total files to scan: $totalFilesCount")
    // Process each directory
    for (rootPath in dirToSearch) {
      addFilesToList(File(rootPath), mFiles, ignoreFolders, ignoreFiles)
    }

    // Group files by MD5 hash
    for (file in mFiles) {
      duplicates.computeIfAbsent(file.md5) { mutableSetOf() }.add(file.filePath)
    }

    // Filter out non-duplicates
    val duplicateDetails = duplicates.filter { it.value.size > 1 }
      .map { DuplicateDetails(it.key, it.value) }

    println("\nDuplicate(s) of (${duplicateDetails.size}) file(s) found.")
    val logFileName =
      "./toDelete" + dirToSearch.joinToString("_") { it.replace(File.separator.toRegex(), "_").replace(" ", "_") }
    println("\nList of duplicate files is stored in $logFileName")

    // Write duplicates to log file
    writeDuplicatesToFile(logFileName, duplicateDetails)

    println("\nProcessing ended at " + Date())
  }

  private fun countFiles(folder: File, ignoreFolders: Set<String>, ignoreFiles: Set<String>) {
    if (folder.isDirectory) {
      if (ignoreFolders.any { folder.resolve(it).exists() }) {
        return
      }
      folder.listFiles()?.forEach { child -> countFiles(child, ignoreFolders, ignoreFiles) }
    } else if (folder.isFile) {
      if (!ignoreFiles.contains(folder.name)) {
        totalFilesCount++
      }
    }
  }

  private fun addFilesToList(
    folder: File,
    mFiles: MutableList<Files>,
    ignoreFolders: Set<String>,
    ignoreFiles: Set<String>
  ) {
    if (folder.isFile) {
      if (!ignoreFiles.contains(folder.name)) {
        counter++
        val path = folder.absolutePath
        val kb = folder.length() / 1024.0
        if (lastMessageLength > 0) print("\r"+" ".repeat(lastMessageLength))
        val message = String.format("\r(%d) files scanned. %s size : %10.2f KB", counter, folder.path, kb)
        lastMessageLength = message.length
        print(message)
        addFileToList(path, mFiles)
      }
    } else if (folder.isDirectory) {
      if (ignoreFolders.any { folder.resolve(it).exists() }) {
        return
      }
      folder.listFiles()?.forEach { child -> addFilesToList(child, mFiles, ignoreFolders, ignoreFiles) }
    }
  }

  private fun addFileToList(filePath: String, mFiles: MutableList<Files>) {
    val md5 = fileToMD5(filePath)
    mFiles.add(Files(md5, filePath))
  }

  private fun fileToMD5(filePath: String): String {
    FileInputStream(filePath).use { inputStream ->
      val buffer = ByteArray(1024)
      val digest = MessageDigest.getInstance("MD5")
      var numRead: Int
      while (inputStream.read(buffer).also { numRead = it } != -1) {
        if (numRead > 0) digest.update(buffer, 0, numRead)
      }
      val md5Bytes = digest.digest()
      return convertHashToString(md5Bytes)
    }
  }

  private fun convertHashToString(md5Bytes: ByteArray): String {
    val returnVal = StringBuilder()
    for (md5Byte in md5Bytes) {
      returnVal.append(((md5Byte.toInt() and 0xff) + 0x100).toString(16).substring(1))
    }
    return returnVal.toString().uppercase(Locale.getDefault())
  }

  private fun writeDuplicatesToFile(fileName: String, duplicateDetails: List<DuplicateDetails>) {
    val logFile = File(fileName)
    try {
      BufferedWriter(FileWriter(logFile)).use { writer ->
        for (details in duplicateDetails) {
          writer.write("${details.md5}\n")
          for (completePath in details.filePath) {
            writer.write("$completePath\n")
          }
        }
      }
    } catch (e: Exception) {
      e.printStackTrace()
    }
  }
}
