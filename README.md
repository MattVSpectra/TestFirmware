# Test grabbing firmware from manifest file

The file *Manifest* contains a series of project names and Git hashes. On every build this project will search the project on Jenkins and find the build that matches that hash. It will grab all artifacts and zip them.
