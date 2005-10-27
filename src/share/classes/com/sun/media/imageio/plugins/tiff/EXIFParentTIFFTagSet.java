/*
 * $RCSfile: EXIFParentTIFFTagSet.java,v $
 *
 * 
 * Copyright (c) 2005 Sun Microsystems, Inc. All  Rights Reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met: 
 * 
 * - Redistribution of source code must retain the above copyright 
 *   notice, this  list of conditions and the following disclaimer.
 * 
 * - Redistribution in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in 
 *   the documentation and/or other materials provided with the
 *   distribution.
 * 
 * Neither the name of Sun Microsystems, Inc. or the names of 
 * contributors may be used to endorse or promote products derived 
 * from this software without specific prior written permission.
 * 
 * This software is provided "AS IS," without a warranty of any 
 * kind. ALL EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND 
 * WARRANTIES, INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY, 
 * FITNESS FOR A PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE HEREBY
 * EXCLUDED. SUN MIDROSYSTEMS, INC. ("SUN") AND ITS LICENSORS SHALL 
 * NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF 
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE FOR 
 * ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT, SPECIAL,
 * CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER CAUSED AND
 * REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF OR
 * INABILITY TO USE THIS SOFTWARE, EVEN IF SUN HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES. 
 * 
 * You acknowledge that this software is not designed or intended for 
 * use in the design, construction, operation or maintenance of any 
 * nuclear facility. 
 *
 * $Revision: 1.2 $
 * $Date: 2005-10-27 22:42:46 $
 * $State: Exp $
 */
package com.sun.media.imageio.plugins.tiff;

import java.util.ArrayList;
import java.util.List;

/**
 * A class containing the TIFF tag used to reference an EXIF IFD.
 * This tag set should be added to the root tag set by means of the 
 * {@link TIFFImageReadParam#addAllowedTagSet(TIFFTagSet)
 * <code>TIFFImageReadParam.addAllowedTagSet</code>} method if EXIF
 * support is desired.
 */
public class EXIFParentTIFFTagSet extends TIFFTagSet {

    private static EXIFParentTIFFTagSet theInstance = null;

    // 34665 - EXIF IFD Pointer                   (LONG/1)
    /** Tag pointing to the EXIF IFD (type LONG). */
    public static final int TAG_EXIF_IFD_POINTER = 34665;

    /** A tag pointing to a GPS info IFD (type LONG). */
    public static final int TAG_GPS_INFO_IFD_POINTER = 34853;

    // To be inserted into parent (root) TIFFTagSet
    static class EXIFIFDPointer extends TIFFTag {
        
        public EXIFIFDPointer() {
            super("EXIFIFDPointer",
                  TAG_EXIF_IFD_POINTER,
                  1 << TIFFTag.TIFF_LONG,
                  EXIFTIFFTagSet.getInstance());
        }
    }

    private static List tags;

    private static void initTags() {
        tags = new ArrayList(1);
        tags.add(new EXIFParentTIFFTagSet.EXIFIFDPointer());
    }

    private EXIFParentTIFFTagSet() {
        super(tags);
    }

    /**
     * Returns a shared instance of an <code>EXIFParentTIFFTagSet</code>.
     *
     * @return an <code>EXIFParentTIFFTagSet</code> instance.
     */
    public synchronized static EXIFParentTIFFTagSet getInstance() {
        if (theInstance == null) {
            initTags();
            theInstance = new EXIFParentTIFFTagSet();
            tags = null;
        }
        return theInstance;
    }
}
