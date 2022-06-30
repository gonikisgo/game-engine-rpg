package cz.cvut.fel.pjv.objects;

import cz.cvut.fel.pjv.objects.mobs.enemies.BossMonster;
import cz.cvut.fel.pjv.objects.mobs.enemies.Monster;
import cz.cvut.fel.pjv.objects.mobs.friends.Elf;
import cz.cvut.fel.pjv.objects.stat1c.*;
import cz.cvut.fel.pjv.objects.stat1c.weapon.BigSword;
import cz.cvut.fel.pjv.objects.stat1c.weapon.Sword;
import cz.cvut.fel.pjv.screen.GamePanel;

/*
class for setting objects on the map and load inventory
 */

public class ObjectSet {
    GamePanel gamePanel;
    // game map size is 60*45, constructor map is only 40*30
    public int tilesOffsetX = 10; // offset X to locate the tile from smaller map on big one
    public int tilesOffsetY = 8; // offset Y to locate the tile from smaller map on big one
    int coinsCount, doorsCount, keysCount, potionCount, treesCount, waterCount, monstersCount, elvesCount;

    public ObjectSet(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        coinsCount = gamePanel.readJsonInfo.dictionary.get("Coin").size();
        doorsCount = gamePanel.readJsonInfo.dictionary.get("Door").size();
        keysCount = gamePanel.readJsonInfo.dictionary.get("Key").size();
        potionCount = gamePanel.readJsonInfo.dictionary.get("Potion").size();
        treesCount = gamePanel.readJsonInfo.dictionary.get("Tree").size();
        waterCount = gamePanel.readJsonInfo.dictionary.get("Water").size();
        monstersCount = gamePanel.readJsonInfo.dictionary.get("Monster").size();
        elvesCount = gamePanel.readJsonInfo.dictionary.get("Elf").size();
    }

    public void objectSetter() {
        // set objects to (x, y) tiles on the map
        setPlayer();

        int offset = 0;

        for (int i = 0; i < coinsCount; i++) {
            gamePanel.allObjects.add(new Coin(gamePanel));
            gamePanel.allObjects.get(offset).objectWorldX = GamePanel.tileSize * (tilesOffsetX + gamePanel.readJsonInfo.dictionary.get("Coin").get(i).getX());
            gamePanel.allObjects.get(offset).objectWorldY = GamePanel.tileSize * (tilesOffsetY + gamePanel.readJsonInfo.dictionary.get("Coin").get(i).getY());
            offset++;
        }

        for (int i = 0; i < doorsCount; i++) {
            gamePanel.allObjects.add(new Door(gamePanel));
            gamePanel.allObjects.get(offset).objectWorldX = GamePanel.tileSize * (tilesOffsetX + gamePanel.readJsonInfo.dictionary.get("Door").get(i).getX());
            gamePanel.allObjects.get(offset).objectWorldY = GamePanel.tileSize * (tilesOffsetY + gamePanel.readJsonInfo.dictionary.get("Door").get(i).getY());
            offset++;
        }

        for (int i = 0; i < keysCount; i++) {
            gamePanel.allObjects.add(new Key(gamePanel));
            gamePanel.allObjects.get(offset).objectWorldX = GamePanel.tileSize * (tilesOffsetX + gamePanel.readJsonInfo.dictionary.get("Key").get(i).getX());
            gamePanel.allObjects.get(offset).objectWorldY = GamePanel.tileSize * (tilesOffsetY + gamePanel.readJsonInfo.dictionary.get("Key").get(i).getY());
            offset++;
        }

        for (int i = 0; i < potionCount; i++) {
            gamePanel.allObjects.add(new Potion(gamePanel));
            gamePanel.allObjects.get(offset).objectWorldX = GamePanel.tileSize * (tilesOffsetX + gamePanel.readJsonInfo.dictionary.get("Potion").get(i).getX());
            gamePanel.allObjects.get(offset).objectWorldY = GamePanel.tileSize * (tilesOffsetY + gamePanel.readJsonInfo.dictionary.get("Potion").get(i).getY());
            offset++;
        }

        for (int i = 0; i < treesCount; i++) {
            gamePanel.allObjects.add(new Tree(gamePanel));
            gamePanel.allObjects.get(offset).objectWorldX = GamePanel.tileSize * (tilesOffsetX + gamePanel.readJsonInfo.dictionary.get("Tree").get(i).getX());
            gamePanel.allObjects.get(offset).objectWorldY = GamePanel.tileSize * (tilesOffsetY + gamePanel.readJsonInfo.dictionary.get("Tree").get(i).getY());

            offset++;
        }

        for (int i = 0; i < waterCount; i++) {
            gamePanel.allObjects.add(new Water(gamePanel));
            gamePanel.allObjects.get(offset).objectWorldX = GamePanel.tileSize * (tilesOffsetX + gamePanel.readJsonInfo.dictionary.get("Water").get(i).getX());
            gamePanel.allObjects.get(offset).objectWorldY = GamePanel.tileSize * (tilesOffsetY + gamePanel.readJsonInfo.dictionary.get("Water").get(i).getY());
            offset++;
        }

        offset = 0;
        for (int i = 0; i < monstersCount; i++) {
            if (gamePanel.readJsonInfo.dictionary.get("Monster").get(i).getOptions() != null) {
                if (gamePanel.readJsonInfo.dictionary.get("Monster").get(i).getOptions().equals("Monster")) {

                    gamePanel.allMobs.add(new Monster(gamePanel));
                    gamePanel.allMobs.get(offset).health = gamePanel.readJsonInfo.dictionary.get("Monster").get(i).getHealth();

                } else if (gamePanel.readJsonInfo.dictionary.get("Monster").get(i).getOptions().equals("BossMonster")) {

                    gamePanel.allMobs.add(new BossMonster(gamePanel));
                    gamePanel.allMobs.get(offset).health = gamePanel.readJsonInfo.dictionary.get("Monster").get(i).getHealth();
                }
            } else {
                if (gamePanel.basicLevel.isBossLevel) {
                    int randomBossMob = gamePanel.rand.nextInt(2);
                    switch (randomBossMob) {
                        case 0:
                            gamePanel.allMobs.add(new BossMonster(gamePanel));
                            break;
                        default:
                            gamePanel.allMobs.add(new Monster(gamePanel));
                    }
                } else {
                    gamePanel.allMobs.add(new Monster(gamePanel));
                }
            }
            gamePanel.allMobs.get(offset).setDefault((tilesOffsetX + gamePanel.readJsonInfo.dictionary.get("Monster").get(i).getX()), (tilesOffsetY + gamePanel.readJsonInfo.dictionary.get("Monster").get(i).getY()));
            gamePanel.allMobs.get(offset).setMoveArea();

            gamePanel.levelHealth += gamePanel.allMobs.get(offset).health;
            offset++;
        }
        gamePanel.levelHealth = gamePanel.levelHealth == 0 ? 1 : gamePanel.levelHealth; // zero division exception, if there are no monsters in the game

        for (int i = 0; i < elvesCount; i++) {
            gamePanel.allMobs.add(new Elf(gamePanel));
            gamePanel.allMobs.get(offset).setDefault((tilesOffsetX + gamePanel.readJsonInfo.dictionary.get("Elf").get(i).getX()), (tilesOffsetY + gamePanel.readJsonInfo.dictionary.get("Elf").get(i).getY()));
            gamePanel.allMobs.get(offset).setMoveArea();
            offset++;
        }

        if (gamePanel.readJsonInfo.dictionary.get("Progress") != null) {
            gamePanel.totalDamageOnMonster = gamePanel.readJsonInfo.dictionary.get("Progress").get(0).getX();
            gamePanel.levelHealth = gamePanel.readJsonInfo.dictionary.get("Progress").get(0).getY();
        }
    }


    private void setPlayer() {
        gamePanel.player.setDefault(tilesOffsetX + gamePanel.readJsonInfo.dictionary.get("Player").get(0).getX(), gamePanel.readJsonInfo.dictionary.get("Player").get(0).getY() + 8);

        if (gamePanel.readJsonInfo.dictionary.get("Player").get(0).getHealth() != -1) {
            gamePanel.player.health = gamePanel.readJsonInfo.dictionary.get("Player").get(0).getHealth();
        }
        setPlayerInventory();
    }

    private void setPlayerInventory() {
        if (gamePanel.readJsonInfo.dictionary.get("PlayerSpeed") != null) {
            gamePanel.player.speed = gamePanel.readJsonInfo.dictionary.get("PlayerSpeed").get(0).getX();
        }

        if (gamePanel.readJsonInfo.dictionary.get("PlayerKey") != null) {
            gamePanel.player.keys = gamePanel.readJsonInfo.dictionary.get("PlayerKey").get(0).getX();
        }

        if (gamePanel.readJsonInfo.dictionary.get("PlayerPotion") != null) {
            gamePanel.player.potionDrunk = gamePanel.readJsonInfo.dictionary.get("PlayerPotion").get(0).getX();
        }

        if (gamePanel.readJsonInfo.dictionary.get("PlayerCoin") != null) {
            gamePanel.player.coins = gamePanel.readJsonInfo.dictionary.get("PlayerCoin").get(0).getX();
        }

        if (gamePanel.readJsonInfo.dictionary.get("PlayerWeapon") != null) {
            if (gamePanel.readJsonInfo.dictionary.get("PlayerWeapon").get(0).getX() == 1) {
                gamePanel.player.weapon[0] = new Sword(gamePanel);
            }
            if (gamePanel.readJsonInfo.dictionary.get("PlayerWeapon").get(0).getY() == 1) {
                gamePanel.player.weapon[1] = new BigSword(gamePanel);
            }
        }
    }
}
