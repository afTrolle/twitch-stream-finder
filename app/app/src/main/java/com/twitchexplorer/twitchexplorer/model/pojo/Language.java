/*
 * This file is generated by jOOQ.
*/
package com.twitchexplorer.twitchexplorer.model.pojo;


import java.io.Serializable;


@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Language implements Serializable {

    private static final long serialVersionUID = 1239841711;

    private final Integer languageId;
    private final String name;

    public Language(Language value) {
        this.languageId = value.languageId;
        this.name = value.name;
    }

    public Language(
            Integer languageId,
            String name
    ) {
        this.languageId = languageId;
        this.name = name;
    }

    public Integer getLanguageId() {
        return this.languageId;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Language (");

        sb.append(languageId);
        sb.append(", ").append(name);

        sb.append(")");
        return sb.toString();
    }
}
