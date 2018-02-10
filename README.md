# NFPA-ChemID-v3
A simple Java program designed to convert NFPA 704 fire diamond information to
chemical names and properties.


## Introduction to NFPA 704
**NFPA 704** is a safety standard created by the National Fire Prevention Agency
that is the cause for diamond-shaped signs which help emergency personnel to
understand and effectively respond to chemical hazards. In three of the
categories, chemicals are assigned scores between 0 and 4, with 0 being the
least hazardous and 4 being the most:

  - *Health* (blue): the danger posed to human health
  - *Flammability* (red): the flammability of the chemical(s)
  - *Reactivity* (yellow): the reactivity, aside from flammability

See [ChemProp.java](https://github.com/claytsay/NFPA-ChemID-v3/blob/master/src/com/tsaysoft/nfpacid3/ChemProp.java) for more information on the relation between the assigned
numbers and the hazards posed.

The last category has symbols that indicate special properties of the chemicals:

  - *OX*: is an oxidizer (e.g. potassium nitrate, KNO3)
  - *SA*: is a simple asphyxiant gas (e.g. nitrogen gas, N2)
  - *~~W~~*: reacts violently with water (e.g. methyl isocyanate, CH3NCO)

For example, the compound sodium hypochlorite (NaClO) has the following ratings:

  - Health: 2 -> Intense or continued exposure could cause harm
  - Flammability: 0 -> Will not burn under typical fire conditions
  - Reactivity: 1 -> Normally stable, but can be unstable in specific situations
  - OX -> Is an oxidizer (i.e. will allow fires to burn without air)


## Program Functions
A quick run-down of the functioning of this program:
  1. A user inputs a certain combination of NFPA 704 information (presumably
    obtained by looking at a metal sign)
  2. The program accesses numerous databases (JSON files) to compile a list of
  chemicals that match the information.
  3. The program obtains information regarding that list of chemicals (e.g.
    melting point, common uses)

Currently, this program is in the pre-alpha stage of development, having little
functionality and almost no UI. In its current state, it can take basic user
input. \(\^\_\^\)


## Program To-Do
  - [ ] Write more Javadocs and add more tags to existing ones
  - [ ] Figure out how to exclude certain files from being uploaded to GitHub
  - [ ] Acquire more databases (especially Wikipedia/MediaWiki)
  - [ ] Get the IDGManager class working
  - [ ] Improve chemical name String "cleaning"
  - [X] Find a way to indicate program versions
  - [X] Find a (possibly) more reliable way to convert chemical name to ID
  - [X] Fix the program so that it actually works
  - [X] Implement ways to HTTP request different chemical ID forms

## Project History
Being one to live in suburban areas, I am no stranger to NFPA 704 fire diamonds.
Once I learned how to read them, I wanted to be able to know what chemicals
specifically were in certain areas. I came up with the idea for this app in 2014.

My first attempt at making an app utilized [Scratch](https://scratch.mit.edu/), a rudimentary
"language" in which I hard-coded NFPA 704 values in. (It took quite a while to
get all those if-then statements to work.) See the project [here](https://scratch.mit.edu/projects/25674264/).

My second attempt involved the use of [AppInventor](http://appinventor.mit.edu/explore/), a more advanced but still
Scratch-like "language." This time, I tried to make a database to store all the
chemical information and got a "legitimately-working" app. However, the UI was
quite limited and the app overall was quite unpolished.

Currently, I am on my third attempt at realizing the idea of NFPA 704
identification. Once this code works, I am planning to use Android Studio to
create a clean and user-friendly app that I can use. (Somehow, I doubt that
any sane person would find this app as interesting as I do.)

## Further reading
  - [NFPA 704](https://en.wikipedia.org/wiki/NFPA_704)
  - [Sodium hypochlorite](https://en.wikipedia.org/wiki/Sodium_hypochlorite) ("active ingredient" in bleach)
  - [Chemical Translation Service](https://www.ncbi.nlm.nih.gov/pmc/articles/PMC2951090/)
  - [OPSIN](http://opsin.ch.cam.ac.uk/information.html)

## Miscellaneous
This README.md was created with the help of this [GitHub guide](https://help.github.com/articles/basic-writing-and-formatting-syntax/).
The Javadocs were created with the help of Wikipedia's [Javadoc](https://en.wikipedia.org/wiki/Javadoc) article.
