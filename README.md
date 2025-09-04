File Directory Structure
<img width="433" height="669" alt="image" src="https://github.com/user-attachments/assets/f8be62d2-f365-4b08-9e1c-40813325583e" />


Dependencies (app level)

    val viewModelVersion = "2.9.2"
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$viewModelVersion")

    /*Room Database Dependencies*/
    val roomVersion = "2.7.2"
    implementation("androidx.room:room-runtime:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")
    /*Optional 1 : Room - kotlin extention and coroutines support */
    implementation("androidx.room:room-ktx:$roomVersion")
    /*Optional 2 : Room - paging support*/
    implementation("androidx.room:room-paging:$roomVersion")

    //Navigation
    val navVersion = "2.9.3"
    implementation("androidx.navigation:navigation-compose:$navVersion")
    //Icons
    implementation("androidx.compose.material:material-icons-extended:1.7.8")


