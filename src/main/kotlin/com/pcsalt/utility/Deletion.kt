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

import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class Deletion {
  fun delete(args: List<String>) {
    if (args.isEmpty()) {
      println("Provide a file to delete a list")
      return
    }
    var br: BufferedReader? = null

    try {
      var sCurrentLine: String?
      br = BufferedReader(FileReader(args[0]))
      while ((br.readLine().also { sCurrentLine = it }) != null) {
        // Check if the line starts with '#' and ignore it
        if (sCurrentLine!!.trim().startsWith("#")) {
          continue // Skip the line
        }

        // Attempt to delete the file
        val fileToDelete = File(sCurrentLine)
        if (fileToDelete.exists()) {
          val size = fileToDelete.length() / 1024
          if (fileToDelete.delete()) {
            println("Deleted: ${fileToDelete.absolutePath} | Size: $size kb")
          } else {
            println("Failed to delete: ${fileToDelete.absolutePath}")
          }
        } else {
          println("File not found: ${fileToDelete.absolutePath}")
        }
      }
    } catch (e: Exception) {
      e.printStackTrace()
    } finally {
      try {
        br?.close()
      } catch (ex: Exception) {
        ex.printStackTrace()
      }
    }
  }
}
