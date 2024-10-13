Search-and-Delete-duplicate-files
=================================

This tool scans specified directories for duplicate files, automatically excluding common project and system directories. It helps users reclaim disk space by identifying and removing redundant files with ease.

## Prerequisites

1. **JDK 17** must be installed.
2. Path environments must be set.

## How to use this

Follow the steps to use it:

1. Download the JAR file: `search-and-delete-duplicates-1.0-SNAPSHOT.jar`.
2. Open `Terminal` or `Command Prompt`.
3. Run the command to find duplicates:
   ```shell
   java -jar search-and-delete-duplicates-1.0-SNAPSHOT.jar --find <root-directory-to-search-duplicate-entries>
   ```
4. After processing is completed, a file will be generated with a list of duplicate files. Open the file and REMOVE THE ENTRIES WHICH YOU WANT TO KEEP.
5. To delete the files listed in the `toDelete` file, run:
   ```shell
   java -jar search-and-delete-duplicates-1.0-SNAPSHOT.jar --delete <file-location>
   ```

## Usage
```shell
java -jar search-and-delete-duplicates-1.0-SNAPSHOT.jar --find <directory1> <directory2> [...]
java -jar search-and-delete-duplicates-1.0-SNAPSHOT.jar --delete <file>
```

## Examples
```shell
java -jar search-and-delete-duplicates-1.0-SNAPSHOT.jar --find ~/dir1 ~/dir2
java -jar search-and-delete-duplicates-1.0-SNAPSHOT.jar --delete toDelete.txt
```

## NOTE
1. File once deleted cannot be recovered. They will be deleted permanently. So, be careful while using `Deletion`.
2. Whenever you want to escape, press `<ctrl>+c` [^c] to exit.

## Ignored Directories and Files

When the program searches for duplicate files, it ignores specific directories and files to streamline the process and avoid unnecessary scanning of commonly used directories.

### Ignored Directories
The following directories are excluded from the search:
- **.git**: This directory is used for version control by Git, and its contents are not relevant to duplicate file searching.
- **build**: This directory often contains compiled files generated during the build process, which are not considered duplicates.
- **node_modules**: This directory is used by Node.js projects to store dependencies and is typically large, so it is ignored.
- **.gradle**: This directory contains Gradle-specific files and caches, which do not need to be scanned.
- **.idea**: This directory is used by JetBrains IDEs (like IntelliJ IDEA) to store project-specific settings and configurations.

### Ignored Files
The program also ignores the following file:
- **.DS_Store**: This is a file created by macOS to store custom attributes of a folder, and it is not useful for the duplicate file search.

By excluding these directories and files, the program focuses on relevant files, enhancing performance and accuracy.

Feel free to reorganize the code and distribute your own version. I would be happy if you could state your name and application in which this code is used.

Send email with the following details:

- **To:** krishna[at]pcsalt[dot]com  
- **Subject:** [Search-and-Delete-duplicate-files]  
- **Message:** [your-text]  

Thank you.

## License
Copyright 2014 Krrishnaaaa

Licensed under the Apache License, Version 2.0 (the "License");  
you may not use this file except in compliance with the License.  
You may obtain a copy of the License at  
[Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0)  
Unless required by applicable law or agreed to in writing, software  
distributed under the License is distributed on an "AS IS" BASIS,  
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  
See the License for the specific language governing permissions and limitations under the License.