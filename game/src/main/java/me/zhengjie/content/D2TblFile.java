/*******************************************************************************
 * 
 * Copyright 2007 Randall
 * 
 * This file is part of gomule.
 * 
 * gomule is free software; you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 * 
 * gomule is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * gomlue; if not, write to the Free Software Foundation, Inc., 51 Franklin St,
 * Fifth Floor, Boston, MA 02110-1301 USA
 *  
 ******************************************************************************/
package me.zhengjie.content;



import com.alibaba.fastjson.JSONObject;

import javax.xml.crypto.Data;
import java.io.File;
import java.util.HashMap;


/**
 * @author Marco
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class D2TblFile 
{



    
    public static String getString(String pKey)
    {
        //将ENG_PATCH作为json对象打印出来

        String lValue = DataMap.PATCH_MAP.get(pKey);
        
        if ( lValue == null )
        {
            lValue = DataMap.EXP2_MAP.get(pKey);
            if ( lValue == null )
            {
                lValue = DataMap.STRING_MAP.get(pKey);
            }
        }
        
        return lValue;
    }
    

	
    
}
