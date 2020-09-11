getTree <- function(hero_id) {
  setwd('C:\\data\\out')
  data <- read.csv(paste(paste( 'teste_hero',hero_id, sep=""), '.csv', sep=""))
  data$player.item_0 <- as.factor(data$player.item_0)
  data$player.item_1 <- as.factor(data$player.item_1)
  data$player.item_2 <- as.factor(data$player.item_2)
  data$player.item_3 <- as.factor(data$player.item_3)
  data$player.item_4 <- as.factor(data$player.item_4)
  data$player.item_5 <- as.factor(data$player.item_5)
  install.packages('caret')
  library(caret)
  install.packages('rpart')
  library(rpart)
  library(rpart.plot)
  install.packages('ggplot2')
  library(ggplot2)
  install.packages('caTools')
  library(caTools)
  
  spl <- sample.split(data$player.win, SplitRatio = 0.7)
  Train <- subset(data, spl==TRUE)
  Test <- subset(data, spl==FALSE)
  res1 <- rpart(player.win~player.item_0+player.item_1+player.item_2+player.item_3+player.item_4+player.item_5, 
               data=Train, 
               method="class", 
               minbucket=15)
  predict <- predict(res1, newdata = Test, type="class")
  table(Test$player.win, predict)
  pdf('pdfs\\treeCV.pdf') 
  prp(res1)
  dev.off()
}