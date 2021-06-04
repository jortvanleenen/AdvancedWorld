package me.jortpepe.advancedworld;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.jortpepe.advancedworld.ErrorHandler.ErrorType;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class AdvancedWorld extends PlaceholderExpansion {

  private static String[] args;
  private static World playerWorld;
  private static Location playerLocation;

  /**
   * States that the Expansion can load without the need of an external plugin.
   *
   * @return True, to indicate the above.
   */
  @Override
  public boolean canRegister() {
    return true;
  }

  /**
   * Sets the identifier ('first argument') for the placeholders of this expansion.
   * Example: %world_x%
   *
   * @return The string of the identifier.
   */
  @Override
  public String getIdentifier() {
    return "world";
  }

  /**
   * Sets the author of this expansion.
   *
   * @return The string of the author's username.
   */
  @Override
  public String getAuthor() {
    return "jortpepe";
  }

  /**
   * Sets the version of this expansion as the version in the build.gradle file.
   * If run from an IDE, 'DEVELOPMENT' shall be returned as version string.
   *
   * @return The string of the version number.
   */
  public String getVersion() {
    return getClass().getPackage().getImplementationVersion() == null ?
        "DEVELOPMENT" : getClass().getPackage().getImplementationVersion();
  }

  /**
   * Handles placeholder requests made to this extension.
   *
   * @param pOffline The player that is requested for the placeholder.
   * @param value A string containing the value of the placeholder behind the expansions identifier.
   * @return A possibly-null String depending on whether the placeholder was found or not.
   */
  public String onRequest(OfflinePlayer pOffline, String value){
    final Player player = pOffline.getPlayer();

    if (player == null) {
      return ErrorHandler.handle(ErrorType.PLAYER);
    }

    args = value.split("_");
    playerWorld = player.getWorld();
    playerLocation = player.getLocation();

    return switch (args[0]) {
      // %world_name%
      case ("name") -> playerWorld.getName();
      // %world_allowanimals%
      case ("allowanimals") -> Boolean.toString(playerWorld.getAllowAnimals());
      // %world_allowmonsters%
      case ("allowmonsters") -> Boolean.toString(playerWorld.getAllowMonsters());
      // %world_minheight%
      case ("minheight") -> Integer.toString(playerWorld.getMinHeight());
      // %world_maxheight%
      case ("maxheight") -> Integer.toString(playerWorld.getMaxHeight());
      // %world_pvp%
      case ("pvp") -> Boolean.toString(playerWorld.getPVP());
      // %world_seed%
      case ("seed") -> Long.toString(playerWorld.getSeed());
      // %world_sealevel%
      case ("sealevel") -> Integer.toString(playerWorld.getSeaLevel());
      // %world_ambientlimit%
      case ("ambientlimit") -> Integer.toString(playerWorld.getAmbientSpawnLimit());
      // %world_animallimit%
      case ("animallimit") -> Integer.toString(playerWorld.getAnimalSpawnLimit());
      // %world_waterambientlimit%
      case ("waterambientlimit") -> Integer.toString(playerWorld.getWaterAmbientSpawnLimit());
      // %world_wateranimallimit%
      case ("wateranimallimit") -> Integer.toString(playerWorld.getWaterAnimalSpawnLimit());
      // %world_ambientticks%
      case ("ambientticks") -> Long.toString(playerWorld.getTicksPerAmbientSpawns());
      // %world_animalticks%
      case ("animalticks") -> Long.toString(playerWorld.getTicksPerAnimalSpawns());
      // %world_waterticks%
      case ("waterticks") -> Long.toString(playerWorld.getTicksPerWaterSpawns());
      // %world_monsterticks%
      case ("monsterticks") -> Long.toString(playerWorld.getTicksPerMonsterSpawns());
      // %world_chunkcount%
      case ("chunkcount") -> Integer.toString(playerWorld.getChunkCount());
      // %world_difficulty%
      case ("difficulty") -> playerWorld.getDifficulty().toString().toLowerCase();
      // %world_environment%
      case ("environment") -> playerWorld.getEnvironment().toString();
      // %world_playercount%
      case ("playercount") -> Integer.toString(playerWorld.getPlayerCount());
      // %world_playerlist%
      case ("playerlist") -> playerWorld.getPlayers().toString();
      // %world_raidlist%
      case ("raidlist") -> playerWorld.getRaids().toString();
      // %world_entitylist%
      case ("entitylist") -> playerWorld.getEntities().toString();
      // %world_livingentitylist%
      case ("livingentitylist") -> playerWorld.getLivingEntities().toString();
      // %world_entitycount%
      case ("entitycount") -> Integer.toString(playerWorld.getEntityCount());
      // %world_tentitycount%
      case ("tentitycount") -> Integer.toString(playerWorld.getTileEntityCount());
      // %world_ttentitycount%
      case ("ttentitycount") -> Integer.toString(playerWorld.getTickableTileEntityCount());
      // %world_moonphase%
      case ("moonphase") -> playerWorld.getMoonPhase().toString().toLowerCase().replace("_", " ");
      // %world_spawninmemory%
      case ("spawninmemory") -> Boolean.toString(playerWorld.getKeepSpawnInMemory());
      // %world_viewdistance%
      case ("viewdistance") -> Integer.toString(playerWorld.getViewDistance());
      // %world_notickdistance%
      case ("notickdistance") -> Integer.toString(playerWorld.getNoTickViewDistance());
      // %world_biome_X%
      // %world_block_X%
      // %world_highestblock_X%
      // %world_highestblocky_X%
      // %world_temperature_X%
      // %world_humidity_X%
      // %world_chunk_X%
      case ("biome"), ("block"), ("highestblock"), ("highestblocky"), ("temperature"), ("humidity"), ("chunk") -> getLocation();
      // %world_folder%
      case ("folder") -> playerWorld.getWorldFolder().toString();
      // %world_coordscale%
      case ("coordscale") -> Double.toString(playerWorld.getCoordinateScale());
      // %world_border_X%
      case ("border") -> getBorder();
      // %world_infiniburn%
      case ("infiniburn") -> playerWorld.getInfiniburn().toString();
      // %world_uid%
      case ("uid") -> playerWorld.getUID().toString();
      // %world_key%
      case ("key") -> playerWorld.getKey().toString();
      // %world_spawnlocation%
      case ("spawnlocation") -> playerWorld.getSpawnLocation().toString();
      // %world_generator%
      case ("generator") -> playerWorld.getGenerator() == null ?
          ErrorHandler.handle(ErrorType.GENERATOR) : playerWorld.getGenerator().toString();
      // %world_forceloadedchunks%
      case ("forceloadedchunks") -> Integer.toString(playerWorld.getForceLoadedChunks().size());
      // %world_populators%
      case ("populators") -> playerWorld.getPopulators().toString();
      // %world_wduration_X%
      // %world_cwduration_X%
      // %world_tduration_X%
      // %world_time_X%
      // %world_fulltime_X%
      // %world_gametime_X%
      case ("wduration"), ("cwduration"), ("tduration"), ("time"), ("fulltime"), ("gametime") -> getTime();

//      TODO: implement methods belows.
//      playerWorld.getEnderDragonBattle(); // returns null if not end
//      playerWorld.getEntitiesByClass();
//      playerWorld.getGameRuleValue(ANNOUNCE_ADVANCEMENTS);
//      playerWorld.getNearbyPlayers(playerLocation, 4, 4, 4);
//      playerWorld.getNearbyLivingEntities(playerLocation, 4, 4, 4);
//      playerWorld.getNearbyEntities(playerLocation, 4, 4, 4, (entity) -> entity.getType() == EntityType.EGG);
      default -> ErrorHandler.handle(ErrorType.PLACEHOLDER);
    };
  }

  /**
   * This method is called by all placeholders that make use of any sort of time arguments.
   *
   * @return The correct placeholder value or an error.
   */
  private static String getTime() {

    if (args.length == 1) {
      return switch (args[0]) {
        case ("cwduration") -> Long.toString(playerWorld.getClearWeatherDuration());
        case ("wduration") -> Long.toString(playerWorld.getWeatherDuration());
        case ("tduration") -> Long.toString(playerWorld.getThunderDuration());
        case ("time") -> Long.toString(playerWorld.getTime());
        case ("fulltime") -> Long.toString(playerWorld.getFullTime());
        case ("gametime") -> Long.toString(playerWorld.getGameTime());
        default -> ErrorHandler.handle(ErrorType.PLACEHOLDERSUB);
      };
    } else {
      return ErrorHandler.handle(ErrorType.ARGUMENTAMOUNT);
    }
  }

  /**
   * This method is called by all placeholders that make use of any sort of border arguments.
   *
   * @return The correct placeholder value or an error.
   */
  private static String getBorder() {

    if (args.length != 2) {
      return ErrorHandler.handle(ErrorType.ARGUMENTAMOUNT);
    }

    WorldBorder border = playerWorld.getWorldBorder();

    return switch (args[1]) {
      // %world_border_center%
      case ("center") -> border.getCenter().toString();
      // %world_border_damagebuffer%
      case ("damagebuffer") -> Double.toString(playerWorld.getWorldBorder().getDamageBuffer());
      // %world_border_damageamount%
      case ("damageamount") -> Double.toString(playerWorld.getWorldBorder().getDamageAmount());
      // %world_border_size%
      case ("size") -> Double.toString(playerWorld.getWorldBorder().getSize());
      // %world_border_warningdistance%
      case ("warningdistance") -> Integer.toString(playerWorld.getWorldBorder().getWarningDistance());
      // %world_border_warningtime%
      case ("warningtime") -> Integer.toString(playerWorld.getWorldBorder().getWarningTime());
      default -> ErrorHandler.handle(ErrorType.PLACEHOLDERSUB);
    };
  }

  /**
   * This method is called by all placeholders that make use of any sort of location arguments.
   * It verifies whether the correct (amount of) arguments have been put into the placeholder and
   * what method to execute (/placeholder to calculate) based on the passed LocationMode ENUM parameter.
   *
   * @return The correct placeholder value or an error.
   */
  private static String getLocation() {
    try {
      return switch (args.length) {
        case (1) -> switch (args[0]) {
          // %world_biome%
          case ("biome") -> playerWorld.getBiome(playerLocation.getBlockX(),
              playerLocation.getBlockY(), playerLocation.getBlockZ()).toString();
          // %world_block%
          case ("block") -> playerWorld.getBlockAt(playerLocation).toString();
          // %world_chunk%
          case ("chunk") -> playerWorld.getChunkAt(playerLocation).toString();
          // %world_humidity%
          case ("humidity") -> Double.toString(playerWorld.getHumidity(playerLocation.getBlockX(),
              playerLocation.getBlockY(), playerLocation.getBlockZ()));
          // %world_highestblock%
          case ("highestblock") -> playerWorld.
              getHighestBlockAt(playerLocation.getBlockX(), playerLocation.getBlockZ()).toString();
          // %world_highestblocky%
          case ("highestblocky") -> Integer.toString(playerWorld.getHighestBlockYAt(playerLocation.getBlockX(),
              playerLocation.getBlockZ()));
          // %world_temerature%
          case ("temperature") -> Double.toString(playerWorld.getTemperature(playerLocation.getBlockX(),
              playerLocation.getBlockY(), playerLocation.getBlockZ()));
          default -> ErrorHandler.handle(ErrorType.PLACEHOLDERSUB);
        };
        case (3) -> switch (args[0]) {
          // %world_highestblock_x_z%
          case ("highestblock") -> playerWorld.getHighestBlockAt(Integer.parseInt(args[1]),
              Integer.parseInt(args[2])).toString();
          // %world_highestblocky_x_z%
          case ("highestblocky") -> Integer.toString(playerWorld.getHighestBlockYAt(Integer.parseInt(args[1]),
              Integer.parseInt(args[2])));
          default -> ErrorHandler.handle(ErrorType.PLACEHOLDERSUB);
        };
        case (4) -> switch (args[0]) {
          // %world_biome_x_y_z%
          case ("biome") -> playerWorld.getBiome(Integer.parseInt(args[1]),
              Integer.parseInt(args[2]), Integer.parseInt(args[3])).toString();
          // %world_block_x_y_z%
          case ("block") -> playerWorld.getBlockAt(Integer.parseInt(args[1]),
              Integer.parseInt(args[2]), Integer.parseInt(args[3])).toString();
          // %world_chunk_x_y_z%
          case ("chunk") -> playerWorld.getChunkAt(new Location(playerWorld,
              Double.parseDouble(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3]))).toString();
          // %world_humidity_x_y_z%
          case ("humidity") -> Double.toString(playerWorld.getHumidity(Integer.parseInt(args[1]),
              Integer.parseInt(args[2]), Integer.parseInt(args[3])));
          // %world_temperature_x_y_z%
          case ("temperature") -> Double.toString(playerWorld.getTemperature(Integer.parseInt(args[1]),
              Integer.parseInt(args[2]), Integer.parseInt(args[3])));
          default -> ErrorHandler.handle(ErrorType.PLACEHOLDERSUB);
        };
        default -> ErrorHandler.handle(ErrorType.ARGUMENTAMOUNT);
      };
    } catch (NumberFormatException e) {
      e.printStackTrace();
      return ErrorHandler.handle(ErrorType.ARGUMENTTYPE);
    }
  }
}
