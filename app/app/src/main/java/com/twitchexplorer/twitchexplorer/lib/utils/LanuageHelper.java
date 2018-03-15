package com.twitchexplorer.twitchexplorer.lib.utils;

import com.twitchexplorer.twitchexplorer.model.pojo.Language;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class LanuageHelper {

    public void test() {
        String[] languages = Locale.getISOLanguages();

        //   new Locale.Builder().setLanguage("en").setRegion("US").build();
        Map<String, Locale> localeMap = new HashMap<String, Locale>(languages.length);
        for (String language : languages) {
            Locale locale = new Locale(language);
            localeMap.put(locale.getISO3Language(), locale);
        }
    }

    public static List<String> help(List<Language> response) {
        ArrayList<String> ret = new ArrayList<>();
        for (Language language : response) {
            if (language.getName().isEmpty())
                continue;
            String[] derp = language.getName().split("-");
            Locale.Builder build = new Locale.Builder().setLanguage(derp[0]);
            if (derp.length > 1) {
                build.setRegion(derp[1]);
            }
            ret.add(build.build().getDisplayName());
        }
        return ret;
    }
}
