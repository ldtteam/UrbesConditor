package patches.projects

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, remove the project with id = 'Minecolonies_PullRequests'
from your code, and delete the patch script.
*/
deleteProject(RelativeId("Minecolonies_PullRequests"))
