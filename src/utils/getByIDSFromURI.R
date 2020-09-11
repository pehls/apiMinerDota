getByIDSFromURI <- function(list, hero_id){
  # setwd("C:\\Users\\gabri\\git\\APIDotaMiner\\apiMinerDota\\files\\out\\byHero\\test\\00-4")
  
  library(jsonlite)
  link <- "https://api.opendota.com/api/matches/"
  temp <- list()
  retorno <- list()
  data <- list()
  for (i in 1:length(list$match_id)){
    
    tryCatch({
      data <- fromJSON(paste(link,list$match_id[1],sep=""), flatten=TRUE)
      temp = getVariables(data, hero_id)
      retorno <- rbind(retorno,temp)
    },error = function(cond) {
      return (NA)
    }, warning = function(cond) {
      return (NULL)
    })
  }
  setwd('C:\\data\\out')
  write.csv(retorno, file=paste(paste("teste_hero",hero_id, sep=""), ".csv", sep=""))
}
