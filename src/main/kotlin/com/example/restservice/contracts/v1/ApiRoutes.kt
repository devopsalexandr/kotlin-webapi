package com.example.restservice.contracts.v1

open class ApiRoutes {
    companion object {
        const val root: String = "api"

        const val version: String = "v1"

        const val base: String = "/$root/$version";
    }

    class Home {
        companion object {
            const val index: String = base + "/"
        }
    }

    class Identity {
        companion object {
            const val register: String = base + "/register"
            const val login: String = base + "/login"
            const val profile: String = base + "/profile"
        }
    }

}