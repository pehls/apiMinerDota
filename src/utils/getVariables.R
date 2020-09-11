getVariables <- function(data, hero_id) {
  id <- NA
  for(i in 1:length(data$players$hero_id)) {
    if(data$players$hero_id[i]==hero_id) {
      id = i
    } 
    
  }
  if(!is.na(id)) {
    temp <- list()
    temp$match_id                                     <- data$match_id
    temp$game_mode                                    <- data$game_mode
    temp$dire_score                                   <- data$dire_score
    temp$human_players                                <- data$human_players
    temp$lobby_type                                   <- data$lobby_type
    temp$engine                                       <- data$engine
    temp$radiant_win                                  <- data$radiant_win
    temp$radiant_score                                <- data$radiant_score
    temp$player.player_slot                           <- data$players$player_slot[id]
    temp$player.assists                               <- data$players$assists[id]
    temp$player.deaths                                <- data$players$deaths[id]
    temp$player.denies                                <- data$players$denies[id]
    temp$player.hero_damage                           <- data$players$hero_damage[id]
    temp$player.hero_healing                          <- data$players$hero_healing[id]
    temp$player.hero_id                               <- data$players$hero_id[id]
    temp$player.item_0                                <- data$players$item_0[id]
    temp$player.item_1                                <- data$players$item_1[id]
    temp$player.item_2                                <- data$players$item_2[id]
    temp$player.item_3                                <- data$players$item_3[id]
    temp$player.item_4                                <- data$players$item_4[id]
    temp$player.item_5                                <- data$players$item_5[id]
    temp$player.kills                                 <- data$players$kills[id]
    temp$player.leaver_status                         <- data$players$leaver_status[id]
    temp$player.level                                 <- data$players$level[id]
    temp$player.tower_damage                          <- data$players$tower_damage[id]
    temp$player.isRadiant                             <- data$players$isRadiant[id]
    temp$player.win                                   <- data$players$win[id]
    temp$player.lose                                  <- data$players$lose[id]
    temp$player.kda                                   <- data$players$kda[id]
    temp$player.abandons                              <- data$players$abandons[id]
    temp$player.benchmarks.gold_per_min.raw 			    <-data$players$benchmarks.gold_per_min.raw[id]
    temp$player.benchmarks.gold_per_min.pct 			    <-data$players$benchmarks.gold_per_min.pct[id]
    temp$player.benchmarks.hero_damage_per_min.raw 	  <-data$players$benchmarks.gold_per_min.raw[id]
    temp$player.benchmarks.hero_damage_per_min.pct 	  <-data$players$benchmarks.hero_damage_per_min.pct[id]
    temp$player.benchmarks.hero_healing_per_min.raw   <-data$players$benchmarks.hero_healing_per_min.raw[id]
    temp$player.benchmarks.hero_healing_per_min.pct	  <-data$players$benchmarks.hero_healing_per_min.pct[id]
    temp$player.benchmarks.tower_damage.raw			      <-data$players$benchmarks.tower_damage.raw[id]
    temp$player.benchmarks.tower_damage.pct			      <-data$players$benchmarks.tower_damage.pct[id]
    temp$player.benchmarks.xp_per_min.raw 				    <-data$players$benchmarks.xp_per_min.raw [id]
    temp$player.benchmarks.xp_per_min.pct  			      <-data$players$benchmarks.xp_per_min.pct  [id]
    temp$player.benchmarks.kills_per_min.raw 			    <-data$players$benchmarks.kills_per_min.raw [id]
    temp$player.benchmarks.kills_per_min.pct			    <-data$players$benchmarks.kills_per_min.pct[id]
    temp$player.benchmarks.hits_per_min.raw  			    <-data$players$benchmarks.hits_per_min.raw [id] 
    temp$player.benchmarks.hits_per_min.pct 			    <-data$players$benchmarks.hits_per_min.pct [id]
  }
  return(temp)
}