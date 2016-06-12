package net.etfbl.prs.prs112212_z4;

/****************************************************************************
 * Copyright (c) 2016 Elektrotehnicki fakultet
 * Patre 5, Banja Luka
 * <p/>
 * All Rights Reserved
 * <p/>
 * \file net.etfbl.prs.prs112212_z4 Hash
 * \brief
 * This file contains a source code for class Hash
 * <p/>
 * Created on 05.06.2016
 *
 * @Author Milan Maric
 * <p/>
 * \notes
 * <p/>
 * <p/>
 * \history
 * <p/>
 **********************************************************************/
public class Hash {
    static {
        System.loadLibrary("Hash");
    }

    /**
     * This function is used to calculate hash using C programming language
     *
     * @param k String that you want calculate hash for
     * @return hash value
     */
    public native long getHash(String k);
}
