package com.tsaysoft.nfpacid3;

/**
 * The Java interface that unifies chemical ID getters.
 * <p>
 *     Mainly applies to <tt>IDGetter</tt>, its subclasses, and <tt>IDGManager</tt>.
 * </p>
 *
 * @author Clay Tsay
 * @version 00.01.00
 */
public interface IDGInterface {

    /**
     * Uses an HTTP request to convert a chemical name to a specific type of chemical ID.
     * <p>
     *     If the ID in unavailable, will print error to console and return <tt>null</tt>.
     * </p>
     *
     * @param chemName the trivial (common) or formal (IUPAC) name of the chemical
     * @return the chemical's requested ID as a <tt>String</tt>
     *
     * @since 00.01.00
     */
    String requestID(String chemName, ChemID id);
}
