/**
 *  Module for finding a Jenkins build based on a parameter
 */

/**
 * Find all items that are part of jobname (folder)
 * Filter by successful builds
 * Return array of Tuple(build, last commit hash)
 */
def findAllSuccessfulBuilds(String jobname){
  def builds = []
  def job = jenkins.model.Jenkins.instance.getItem(jobname)
  def allItems = job.getAllItems()
  allItems.each{
    it.builds.each{ bd->
      if(hudson.model.Result.SUCCESS == bd.result){
        if(bd.getChangeSets()){
             def lastCommit = bd.getChangeSets()[0].getItems()[0].getCommitId()
          builds.add(new Tuple(bd, lastCommit))
        }
      }
    }
  }
  return builds
}

/**
 * Given a hash, find the build in jobname that matches
 */
def findBuildByHash(String jobname, String hash){
  def result = null
  def builds = findAllSuccessfulBuilds(jobname)
  builds.find{
    if(it[1] == hash){
      result = it[0]
      return true
    }
    return false
  }
  return result
}

return this