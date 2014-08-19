Search-and-Delete-duplicate-files
=================================

	Prerequisites:
		1. JDK must be installed, and
		2. Path environments must be set.

How to use this?

	Follow the steps to use it:
		Step 1: Clone the repo
		Step 2: Open `Terminal` or `Command Prompt`
		Step 3: Navigate to the cloned repo
		Step 4: Compile code `javac *.java`
		Step 5: Run `java ListFiles <root-directory-to-search-duplicate-entries>`
		Step 6: After processing is completed a file will be generated with list of 
			duplicate files. Open the file and REMOVE THE ENTRIES WHICH YOU WANT TO KEEP.
		Step 7: Run 'java Deletion <file-location>`
	
Now it will delete all those files listed in `toDelete` file.

	NOTE:
		1. File once deleted cannot be recovered. They will be deleted permanently.
			So, be careful while using `Deletion`.
		2. Whenever you want to escape press <ctrl>+c [^c] to exit.


Java source code to search a directory, recursively, for duplicate files. Well, this code is not commented, but feel free to query.

Feel free to reorganize code, and distribute your own version. I would be happy if you could state your name and application, in which this code is used.

Send email with following details:

	To:      krishna[at]pcsalt[dot]com
	Subject: [Search-and-Delete-duplicate-files]
	Message: [Name] [Application-Name]

Thank you.

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
