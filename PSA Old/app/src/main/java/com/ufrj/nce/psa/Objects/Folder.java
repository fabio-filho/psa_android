package com.ufrj.nce.psa.Objects;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabiofilho on 3/13/15.
 */
public class Folder {

    private String directoryName, directoryOld;
    private List<File> mList;

    public Folder(){

        mList = new ArrayList<File>();

    }


    public List<File> getListFilesByDirectory(String directoryName) {

        File directory = new File(directoryName);

        File[] fList = directory.listFiles();

        for (File file : fList) {

            if (file.isFile())
                mList.add(file);

            else if (file.isDirectory())
                getListFilesByDirectory(file.getAbsolutePath());

        }

        return mList;

    }


    public String[] getPathsByDirectory(String directoryName) {

        List<File> mList ;

        mList = getListFilesByDirectory(directoryName);

        if(mList.size() == 0) return null;

        String[] finalList = new String[mList.size()];

        for(int index=0; index<mList.size(); index++)
            finalList[index] = mList.get(index).toString();

        return finalList;

    }


}
