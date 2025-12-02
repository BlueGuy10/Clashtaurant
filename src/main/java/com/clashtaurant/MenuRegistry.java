package com.clashtaurant;

// Minimal bootstrap to ensure all menu items are instantiated once
// so they register themselves into FoodItem category lists.
public final class MenuRegistry {
    private static boolean initialized = false;

    private MenuRegistry() {}

    public static synchronized void init() {
        if (initialized) return;
        initialized = true;

        // Mains
        new com.clashtaurant.foods.mains.Barbarianburger();
        new com.clashtaurant.foods.mains.ExecutionersSoup();
        new com.clashtaurant.foods.mains.Fishermanfishandchips();
        new com.clashtaurant.foods.mains.Furnacefirerice();
        new com.clashtaurant.foods.mains.Golemgazpacho();
        new com.clashtaurant.foods.mains.HayDayFarmfriedchicken();
        new com.clashtaurant.foods.mains.HunterVenison();
        new com.clashtaurant.foods.mains.MightyMinerMacaroni();
        new com.clashtaurant.foods.mains.MiniPekaPancakes();
        new com.clashtaurant.foods.mains.MonksVeggieBurger();
        new com.clashtaurant.foods.mains.PheonixEggs();
        new com.clashtaurant.foods.mains.PrinceKebab();
        new com.clashtaurant.foods.mains.RoyalHam();
        new com.clashtaurant.foods.mains.VinesSalad();

        // Desserts
        new com.clashtaurant.foods.desserts.DarkPrinceDonut();
        new com.clashtaurant.foods.desserts.IceSpiritIceCream();
        new com.clashtaurant.foods.desserts.LavaHoundLavaCake();
        new com.clashtaurant.foods.desserts.LogCake();
        new com.clashtaurant.foods.desserts.Motherwitchcookies();
        new com.clashtaurant.foods.desserts.Princesspie();
        new com.clashtaurant.foods.desserts.Snowballsnowcone();

        // Drinks
        new com.clashtaurant.foods.drinks.DarkElixir();
        new com.clashtaurant.foods.drinks.Elixer();
        new com.clashtaurant.foods.drinks.FreezeSlushie();
        new com.clashtaurant.foods.drinks.GoblinBarrelWhisky();
        new com.clashtaurant.foods.drinks.LumberjackBeer();
        new com.clashtaurant.foods.drinks.Zaplingwater();
    }
}
