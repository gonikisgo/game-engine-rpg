package cz.cvut.fel.pjv.handlers;

import cz.cvut.fel.pjv.screen.GamePanel;

import java.util.logging.Level;
import java.util.logging.Logger;

/*
class controls interaction with objects
 */
public class ObjectHandler {
    private final static Logger LOGGER = Logger.getLogger(WeaponHandler.class.getName());
    final static int maxPlayerSpeed = 10;
    GamePanel gamePanel;

    public ObjectHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void playerObjectInteraction() {
        switch (gamePanel.allObjects.get(gamePanel.player.collisionIndex).name) {
            case "Coin":
                gamePanel.player.coins++;
                gamePanel.allObjects.remove(gamePanel.player.collisionIndex);
                break;
            case "Door":
                if (gamePanel.player.keys > 0) {
                    gamePanel.player.keys--;
                    gamePanel.allObjects.remove(gamePanel.player.collisionIndex);
                }
                break;
            case "Key":
                gamePanel.player.keys++;
                gamePanel.allObjects.remove(gamePanel.player.collisionIndex);
                break;
            case "Potion":
                gamePanel.player.potionDrunk++;
                gamePanel.player.speed = gamePanel.player.speed >= maxPlayerSpeed ? maxPlayerSpeed : gamePanel.player.speed + 1;
                gamePanel.allObjects.remove(gamePanel.player.collisionIndex);
                break;
        }
    }

    public void weaponObjectInteraction() {
        if (gamePanel.allObjects.get(gamePanel.player.collisionIndex).name.equals("Tree") && gamePanel.allObjects.get(gamePanel.player.collisionIndex).canBeDestroyed) {
            gamePanel.allObjects.get(gamePanel.player.collisionIndex).underAttack = true;
            gamePanel.allObjects.get(gamePanel.player.collisionIndex).health -= gamePanel.player.currentWeapon.damage;
            if (gamePanel.allObjects.get(gamePanel.player.collisionIndex).health <= 0) {
                gamePanel.allObjects.remove(gamePanel.player.collisionIndex);
                LOGGER.log(Level.INFO, "tree was destroyed");
            }
        }
    }

    public void playerMobInteraction() {
        if (gamePanel.player.frequency == 4) {
            switch (gamePanel.allMobs.get(gamePanel.player.collisionIndex).name) {
                case "Monster":
                    gamePanel.player.health -= 5;
                    break;
                case "BossMonster":
                    gamePanel.player.health -= 20;
                    break;
                case "Elf":
                    if (gamePanel.allMobs.get(gamePanel.player.collisionIndex).canHeal) {
                        gamePanel.player.health += 5;
                    }
                    break;
            }
            gamePanel.player.frequency = 0;
        } else {
            gamePanel.player.frequency++;
        }
    }

    public void weaponMobInteraction() {
        if (gamePanel.player.frequency == 4) {
            int speed = gamePanel.allMobs.get(gamePanel.player.collisionIndex).speed;
            switch (gamePanel.allMobs.get(gamePanel.player.collisionIndex).name) {
                case "Monster":
                    gamePanel.allMobs.get(gamePanel.player.collisionIndex).underAttack = true;
                    gamePanel.allMobs.get(gamePanel.player.collisionIndex).speed = speed >= 4 ? 4 : speed + 1;
                    gamePanel.allMobs.get(gamePanel.player.collisionIndex).health -= gamePanel.player.currentWeapon.damage;
                    gamePanel.totalDamageOnMonster += gamePanel.player.currentWeapon.damage;
                    if (gamePanel.allMobs.get(gamePanel.player.collisionIndex).health <= 0) {
                        gamePanel.allMobs.remove(gamePanel.player.collisionIndex);
                        LOGGER.log(Level.INFO, "monster was killed");
                    }
                    break;
                case "BossMonster":
                    gamePanel.allMobs.get(gamePanel.player.collisionIndex).underAttack = true;
                    gamePanel.allMobs.get(gamePanel.player.collisionIndex).speed = speed >= 10 ? 10 : speed + 1;
                    gamePanel.allMobs.get(gamePanel.player.collisionIndex).health -= gamePanel.player.currentWeapon.damage;
                    gamePanel.totalDamageOnMonster += gamePanel.player.currentWeapon.damage;
                    if (gamePanel.allMobs.get(gamePanel.player.collisionIndex).health <= 0) {
                        gamePanel.allMobs.remove(gamePanel.player.collisionIndex);
                        LOGGER.log(Level.INFO, "boss monster was killed");
                    }
                    break;
            }
            gamePanel.player.frequency = 0;
        } else {
            gamePanel.player.frequency++;
        }
    }
}
