# NFPA-ChemID-v3
A Java programme designed to convert NFPA 704 fire diamond information to chemical names and properties.

NFPA 704 is a safety standard that is the cause for diamond-shaped signs that help emergency personnel to
understand and effectively respond to chemical hazards. In three of the categories, scores between 0 and 4
are assigned, with 0 being the least hazardous and 4 being the most:
  - Health (blue)
  - Flammability (red)
  - Reactivity (yellow)
The last category has symbols that indicate special properties of the chemicals:
  - OX: is an oxidizer (e.g. KNO3)
  - SA: is a simple asphyxiant gas (e.g. N2)
  - W: reacts violently with water (e.g. Na)

A quick run-down of the functioning of this programme:
  1. A user inupts a certain combination of NFPA 704 information (presumably obtained from a metal sign)
  2. The programme accesses numerous databases (JSON files) to compile a list of chemicals that match the information.
  3. The programme obtains information regarding that list of chemicals (e.g. melting point, common uses)

Currently, this programme is in development, having almost no UI. It is mostly limited to being a console programme.
It is so rudimentary that I haven't come up with a system to identify versions yet.

(>_<)
