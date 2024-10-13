# Changelog

## [Version 1.0]

### Added
- Flag-based operations for **find** and **delete** functionalities, allowing users to specify operations more clearly using command-line flags.

### Changed
- Organized the project structure using **Gradle**, making it easier to manage dependencies and build processes.

### Updated
- Integrated JAR file usage for executing the application, simplifying the running process for users.

### Removed
- Manual compilation steps are no longer required, enhancing user experience and reducing setup complexity.

### Examples
- **Find duplicates**:  
  ```bash
  java -jar search-and-delete-duplicates-1.0-SNAPSHOT.jar --find <directory1> <directory2> [...]
  ```

- **Delete files**:
  ```bash
  java -jar search-and-delete-duplicates-1.0-SNAPSHOT.jar --delete <file>
  ```