package com.game.lanceofdestiny;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GameState {
    private String username;
    private int score;
    private int remainingChances;
    private List<Spell> acquiredSpells;
    private List<Barrier> barriers;

    public GameState() {
        acquiredSpells = new ArrayList<>();
        barriers = new ArrayList<>();
    }

    // Getters and setters

    public String toJSON() {
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("username", username);
        jsonObject.addProperty("score", score);
        jsonObject.addProperty("remainingChances", remainingChances);

        JsonArray spellsArray = new JsonArray();
        for (Spell spell : acquiredSpells) {
            spellsArray.add(spell.toJSON());
        }
        jsonObject.add("acquiredSpells", spellsArray);

        JsonArray barriersArray = new JsonArray();
        for (Barrier barrier : barriers) {
            barriersArray.add(barrier.toJSON());
        }
        jsonObject.add("barriers", barriersArray);

        return gson.toJson(jsonObject);
    }

    public static GameState fromJSON(String jsonData) {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(jsonData).getAsJsonObject();

        GameState gameState = new GameState();
        gameState.username = jsonObject.get("username").getAsString();
        gameState.score = jsonObject.get("score").getAsInt();
        gameState.remainingChances = jsonObject.get("remainingChances").getAsInt();

        JsonArray spellsArray = jsonObject.getAsJsonArray("acquiredSpells");
        for (JsonElement spellElement : spellsArray) {
            Spell spell = Spell.fromJSON(spellElement.toString());
            gameState.acquiredSpells.add(spell);
        }

        JsonArray barriersArray = jsonObject.getAsJsonArray("barriers");
        for (JsonElement barrierElement : barriersArray) {
            Barrier barrier = Barrier.fromJSON(barrierElement.toString());
            gameState.barriers.add(barrier);
        }

        return gameState;
    }
}
