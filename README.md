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

The last category has symbols that indicate special properties of the chemicals:
  - *OX*: is an oxidizer (e.g. potassium nitrate, KNO3)
  - *SA*: is a simple asphyxiant gas (e.g. nitrogen gas, N2)
  - *~~W~~*: reacts violently with water (e.g. methyl isocyanate, CH3NCO)

For example, the compound sodium hypochlorite (NaClO) has the following ratings:
  - Health: 2 -> Intense or continued exposure could cause harm
  - Flammability: 0 -> Will not burn under typical fire conditions
  - Reactivity: 1 -> Normally stable, but can be unstable in specific situations
  - OX -> Is an oxidizer (and will allow fires to burn without air)


## Program Functions
A quick run-down of the functioning of this program:
  1. A user inputs a certain combination of NFPA 704 information (presumably
    obtained by looking at a metal sign)
  2. The program accesses numerous databases (JSON files) to compile a list of
  chemicals that match the information.
  3. The program obtains information regarding that list of chemicals (e.g.
    melting point, common uses)

Currently, this program is in the pre-alpha stage of development, having little
functionality and almost no UI. In its current state, it cannot take any user
input. \(\>\_\<\)


## Program To-Do
  - [ ] Write more Javadocs and add more tags to existing ones
  - [ ] Implement ways to HTTP request different chemical ID forms
  - [ ] Find a way to indicate program versions
  - [ ] Figure out how to exclude certain files from being uploaded to GitHub
  - [ ] Acquire more databases (especially Wikipedia/MediaWiki)
  - [ ] Find a more reliable way to convert chemical name to ID

## Further reading
  - [NFPA 704](https://en.wikipedia.org/wiki/NFPA_704)
  - [Sodium hypochlorite](https://en.wikipedia.org/wiki/Sodium_hypochlorite)
  - [Chemical Translation Service](https://www.ncbi.nlm.nih.gov/pmc/articles/PMC2951090/)

## Miscellaneous
This README.md was created with the help of this [GitHub guide](https://help.github.com/articles/basic-writing-and-formatting-syntax/).
