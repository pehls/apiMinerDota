getTreeCV <- function(hero_id) {
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
  install.packages('e1071')
  library(e1071)
  library(rpart.plot)
  install.packages('ggplot2')
  library(ggplot2)
  install.packages('caTools')
  library(caTools)
  
  spl <- sample.split(data$player.win, SplitRatio = 0.7)
  Train <- subset(data, spl==TRUE)
  Test <- subset(data, spl==FALSE)
  numfolds <- trainControl(method = "cv", number=6)
  cpgrid = expand.grid(.cp=seq(0.01,0.5,0.01))
  train <- train(player.win~player.item_0+player.item_1+player.item_2+player.item_3+player.item_4+player.item_5, 
                 data=Train,
                 method="rpart",
                 trControl=numfolds,
                 tuneGrid=cpgrid)
  treecv <-  rpart(player.win~player.item_0+player.item_1+player.item_2+player.item_3+player.item_4+player.item_5, 
                   data=Train,
                   method="class",
                   cp=0.01)
  predictcv <- predict(treecv, newdata=Test, type="class")
  table(Test$player.win, predictcv)
  pdf('pdfs\\treeCV.pdf') 
  prp(treecv)
  dev.off() 
}