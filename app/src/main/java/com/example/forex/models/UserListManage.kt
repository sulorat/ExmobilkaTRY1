package com.example.forex.models

object UserListManage {
    private val users = mutableListOf<User>(
        User("Dima","dima@mail.ru","123123"),
        User("Seva","seva@mail.ru","890890"),
        User("Sergey","seriy@mail.ru","567567"),
        User("Vlad","Vlad@mail.ru","156156")
    )

    fun addUser(user: User) {
        users.add(user)
    }

    fun getUserByEmail(email: String): User? {
        return users.find { it.emailOrPhone.equals(email, ignoreCase = true) }
    }

    fun checkUser(email: String): Boolean {
        return getUserByEmail(email) != null
    }

    fun isEmailTaken(email: String): Boolean {
        return users.any { it.emailOrPhone.equals(email, ignoreCase = true) }
    }

}