package com.AristoPets.webconfig;


public final class URLS {

    public static final String ABSOLUTE_MAIN = "http://aristo-pets.com";
    public static final String ADVERTS_LIST = "/list/adverts";
    public static final String ABSOLUTE_ADVERTS_LIST = ABSOLUTE_MAIN + "/list/adverts";
    public static final String ANIMALS_LIST = "/list/animals";
    public static final String ABSOLUTE_ANIMALS_LIST = ABSOLUTE_MAIN +"/list/animals";
    public static final String ADVERT = "/advert/"; // + {id}
    public static final String ABSOLUTE_ADVERT = ABSOLUTE_MAIN + "/advert/"; // + {id}
    public static final String ANIMAL = "/animal/";
    public static final String ABSOLUTE_ANIMAL = ABSOLUTE_MAIN + "/animal/"; // + {id}
    public static final String USER_PROFILE = "/myprofile";
    public static final String ABSOLUTE_PROFILE = ABSOLUTE_MAIN + "/myprofile";
    public static final String ABOUT_US = "/aboutUs/";
    public static final String BREEDER_ADVERTS = "/breeder/adverts/";
    public static final String ABSOLUTE_BREEDR_ADVERTS = ABSOLUTE_MAIN + "/breeder/adverts/";
    public static final String BREEDER_ANIMALS = "/breeder/animals/";
    public static final String ABSOLUTE_BREEDR_ANIMALS = ABSOLUTE_MAIN + "/breeder/animals/";
    public static final String FAVORITES = "/favorites";
    public static final String FAVORITES_ANIMALS = FAVORITES + "/animals";
    public static final String FAVORITES_ADVERTS = FAVORITES + "/adverts";

    public static final String[] ALL_STATIC_URLS = {ABOUT_US, ADVERT + "*", ADVERTS_LIST,
            ANIMAL + "*", ANIMALS_LIST, USER_PROFILE, BREEDER_ADVERTS + "*", BREEDER_ANIMALS + "*",
            FAVORITES_ANIMALS, FAVORITES_ADVERTS, "/"};

    public static final class API{
        public static final String USER = "/api/user/";
        public static final String PHOTO = "/api/photo/";
        public static final String USER_PHOTO = PHOTO + "user/";
        public static final String ANIMAL_PHOTO = PHOTO + "animal/";
        public static final String ADVERT_PHOTO = PHOTO + "advert/";
        public static final String PARENTS = "/api/parents/";
        public static final String FAVORITE = "/api/favorite/";
        public static final String FAVORITE_ANIMAL = FAVORITE + "animal/";
        public static final String FAVORITE_ADVERT = FAVORITE + "advert/";
        public static final String ABSOLUTE_FAVORITE_ANIMAL = ABSOLUTE_MAIN + FAVORITE_ANIMAL;
        public static final String ABSOLUTE_FAVORITE_ADVERT = ABSOLUTE_MAIN + FAVORITE_ADVERT;
        public static final String ANIMAL = "/api/animal/";
        public static final String ADVERT = "/api/advert/";
        public static final String BREEDER_CONFIRMATION = "/api/breeder/confirm/*";
        public static final String[] API_FILTER = {USER, PHOTO + "*", FAVORITE + "*", ANIMAL + "*",  ADVERT + "*",
                                                    BREEDER_CONFIRMATION};

    }

    public static final String[] AUTH_FILTER_STATIC_PAGES = {USER_PROFILE, FAVORITES_ADVERTS, FAVORITES_ANIMALS, "/api/"};
}