#Changelog

##Introduction
This document is designed to list out the past and present versions
of the program and to document the changes involved with each new version.

##Log
###00.01.00
- "Initial release"
- Changed 'IDGet' to an abstract class to allow for different
ID-getting services to extend 'IDGetter' (the new class)
  - Edited 'Chemical' class
  - Wrote the 'IDGManager' class to manage multiple 'IDGetter's
- Added support for using the OPSIN service
- Organized version numbering and logging documents and guidelines
(see 'VERSION_GUIDELINES.md')
- Refined Javadocs
  - Added '{@link XXX}'s to the class descriptors (not methods)
  - Added '@since' tags to all public methods within classes
  - Added '@version' tags to all classes (presumably to denote
  when said classes were last updated)
