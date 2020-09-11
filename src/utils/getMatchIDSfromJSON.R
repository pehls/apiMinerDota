getMatchIDSfromJSON <- function(nFiles, hero_id) {
  wd <-"C:\\data\\byHero"
  list <- list()
  temp <- list()
  library(jsonlite)
  setwd(paste(paste(wd,"\\00-",sep=""), hero_id, sep=""))
  for(j in 0:nFiles) {
    tryCatch({
      paste("0",j, sep="")
      name <- paste(paste(paste(paste("0",j, sep="") ,"-", sep=""),hero_id,sep=""),".json", sep="")
      data <- fromJSON(name, flatten=TRUE)
      temp$match_id <- data$result$matches$match_id
      list <- rbind(list, as.data.frame(temp))
      
    },error = function(cond) {
      return (NA)
    }, warning = function(cond) {
      return (NULL)
    })
  }
  return (list)
}