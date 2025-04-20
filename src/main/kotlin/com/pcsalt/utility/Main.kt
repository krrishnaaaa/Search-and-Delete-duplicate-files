package com.pcsalt.utility

fun main(args: Array<String>) {
  if (args.isEmpty()) {
    printUsage()
    return
  }

  when {
    args[0] == "--find" && args.size >= 2 -> {
      val directories = args.drop(1)
      println("Find command detected. Searching in directories: $directories")
      ListFiles().find(directories)
    }

    args[0] == "--delete" && args.size == 2 -> {
      val fileToDelete = args.drop(1)
      println("Delete command detected. Deleting files listed in: $fileToDelete")
      Deletion().delete(fileToDelete)
    }

    else -> {
      println("Invalid arguments.")
      printUsage()
    }
  }
}

fun printUsage() {
  println(
    """
        Usage:
          java -jar program.jar --find <directory1> <directory2> [...]
          java -jar program.jar --delete <file>
          
        Examples:
          java -jar program.jar --find ~/dir1 ~/dir2
          java -jar program.jar --delete toDelete.txt
        """.trimIndent()
  )
}
