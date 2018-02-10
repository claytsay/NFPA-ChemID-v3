package com.tsaysoft.nfpacid3;

/**
 * An <tt>enum</tt> that lists the various chemical ID formats.
 * <p>
 *     See the individual Javadocs for each of the <tt>enum</tt>s for further information.
 * </p>
 *
 * @author Clay Tsay
 * @version 00.01.00
 */
public enum ChemID {
    /**
     * Chemical Abstracts Service Registry Number.
     * <p>
     *     The Chemical Abstracts Service (CAS) is a division of the ACS (American Chemical Society).
     *     The numerical ID is called a CASRN (CAS Registry Number) or CAS Number.
     * </p>
     * @see <a href="https://www.cas.org/about/faqs">https://www.cas.org/about/faqs</a>
     * @since 00.01.00
     */
    CASRN,

    /**
     * Compound Identification Number.
     * <p>
     *     The Compound Identification Number (CID) is used by PubChem to identify chemical compounds.
     * </p>
     * @see <a href="https://pubchem.ncbi.nlm.nih.gov/upload/docs/upload_faq.html#UIDtypes">https://pubchem.ncbi.nlm.nih.gov/upload/docs/upload_faq.html#UIDtypes</a>
     * @since 00.01.00
     */
    CID,

    /**
     * International Chemical Identifier - Key version.
     * <p>
     *     The International Chemical Identifier (InChI) was developed by IUPAC and NIST.
     *     <p><b>InChI Code</b> is an ID format that is somewhat readable to chemists.
     *     <p><b>InChI Key</b> is a computer-friendly ID format that is not readable to chemists.
     * </p>
     * @see <a href="https://iupac.org/who-we-are/divisions/division-details/inchi/">https://iupac.org/who-we-are/divisions/division-details/inchi/</a>
     * @since 00.01.00
     */
    InChI_Key;

    /**
     * Overrides the <tt>toString</tt> to print out the abbreviation/acronym of the ID type.
     * @return the non-hashmap "toString" of the <tt>ChemID</tt> type
     *
     * @since 00.01.00
     */
    @Override
    public String toString() {
        switch(this) {
            case CID:
                return "CID";
            case CASRN:
                return "CASRN";
            case InChI_Key:
                return "InChI Key";
        }

        return "Error - unrecognized ChemID";
    }
}
