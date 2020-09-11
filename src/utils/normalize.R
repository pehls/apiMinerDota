normalize <- function (hero_id) {
  setwd('C:\\data\\out')
  data <- read.csv(paste(paste( 'teste_hero',hero_id, sep=""), '.csv', sep=""))
  data$player.item_0 <- as.factor(data$player.item_0)
  data$player.item_1 <- as.factor(data$player.item_1)
  data$player.item_2 <- as.factor(data$player.item_2)
  data$player.item_3 <- as.factor(data$player.item_3)
  data$player.item_4 <- as.factor(data$player.item_4)
  data$player.item_5 <- as.factor(data$player.item_5)
  
  install.packages('caTools')
  library(caTools)
  
  spl <- sample.split(data$player.win, SplitRatio = 0.7)
  Train <- subset(data, spl==TRUE)
  Test <- subset(data, spl==FALSE)
  t<- list()
  t$player.win <- Train$player.win
  t$player.assists <- Train$player.assists
  t$player.denies <- Train$player.denies
  t$player.deaths <- Train$player.deaths
  t$player.kills <- Train$player.kills
  t$player.level <- Train$player.level
  t$player.benchmarks.gold_per_min.raw  <- Train$player.benchmarks.gold_per_min.raw
  t$player.tower_damage <- Train$player.tower_damage
  t$player.hero_damage <- Train$player.hero_damage
  ranges <- list()
  ranges <- sapply(t, function(x) max(x))
  normalized <- t
  normalized$player.assists <- t$player.assists/ranges[2]
  normalized$player.denies <- t$player.denies/ranges[3]
  normalized$player.deaths <- t$player.deaths/ranges[4]
  normalized$player.kills <- t$player.kills/ranges[5]
  normalized$player.level <- t$player.level/ranges[6]
  normalized$player.benchmarks.gold_per_min.raw <- t$player.benchmarks.gold_per_min.raw/ranges[7]
  normalized$player.tower_damage <- t$player.tower_damage/ranges[8]
  normalized$player.hero_damage <- t$player.hero_damage/ranges[9]
  setwd('C:\\data\\out')
  write.csv(retorno, file=paste(paste("normalizedTrain_hero",hero_id, sep=""), ".csv", sep=""))
  t<- list()
  t$player.win <- Test$player.win
  t$player.assists <- Test$player.assists
  t$player.denies <- Test$player.denies
  t$player.deaths <- Test$player.deaths
  t$player.kills <- Test$player.kills
  t$player.level <- Test$player.level
  t$player.benchmarks.gold_per_min.raw  <- Test$player.benchmarks.gold_per_min.raw
  t$player.tower_damage <- Test$player.tower_damage
  t$player.hero_damage <- Test$player.hero_damage
  ranges <- list()
  ranges <- sapply(t, function(x) max(x))
  normalized <- t
  normalized$player.assists <- t$player.assists/ranges[2]
  normalized$player.denies <- t$player.denies/ranges[3]
  normalized$player.deaths <- t$player.deaths/ranges[4]
  normalized$player.kills <- t$player.kills/ranges[5]
  normalized$player.level <- t$player.level/ranges[6]
  normalized$player.benchmarks.gold_per_min.raw <- t$player.benchmarks.gold_per_min.raw/ranges[7]
  normalized$player.tower_damage <- t$player.tower_damage/ranges[8]
  normalized$player.hero_damage <- t$player.hero_damage/ranges[9]
  setwd('C:\\data\\out')
  write.csv(retorno, file=paste(paste("normalizedTest_hero",hero_id, sep=""), ".csv", sep=""))
}