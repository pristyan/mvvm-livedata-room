## Mini App to Show Project Structure

#### Design Pattern
>**MVVM + LiveData.**
>
>I used MVP since 2017. But, for this project, I'm trying to use MVVM. I started to learn this design pattern since early November of this year. So, this is the first project that I use MVVM design pattern.


#### Dummy Data
>**app/src/main/assets/__.json**
>
>I stored my dummy data in ***json** files, under **app/src/main/assets/** directory. When the app launched, **SplashViewModel** would check the data existence in the **SQLite**. If doesn't exist, it would read the json data and store it to the **SQLite**, that I managed with **Room** library.

</br>

#### Login
>Recently, I stored 2 accounts that you can try :
>
>`email : anya@gmail.com | password : 123456`
>
>`email : isyan@gmail.com | password : 654321`
>
>You can add / edit / delete it in **app/src/main/assets/users_response.json**.