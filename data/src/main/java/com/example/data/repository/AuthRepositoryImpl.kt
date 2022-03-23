package com.example.data.repository

import android.util.Log
import androidx.navigation.NavController
import com.example.domain.models.Response
import com.example.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@ExperimentalCoroutinesApi
class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
) : AuthRepository {

    override fun isUserAuthenticatedInFirebase() = auth.currentUser != null

    override suspend fun signIn(email: String, password: String) = flow {
        try {
            emit(Response.Loading)
            auth.signInWithEmailAndPassword(
                email,
                password
            )
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {

                        Log.d("TAG", "WelcomeScreen: Login Success")
                    } else (Log.d("TAG", "Failed: ${task.exception}")
                            )
                }
            emit(Response.Success(true))
        } catch (e: Exception) {
            emit(Error(e.message ?: e.toString()))
        }
    }

    override suspend fun signOut() = flow {
        try {
            emit(Response.Loading)
            auth.signOut()
        } catch (e: Exception) {
            emit(Error(e.message ?: e.toString()))
        }
    }

    private val userCollectionRef = Firebase.firestore.collection("users")
    override suspend fun register(
        email: String,
        password: String,
        name: String,
        mobile_no: String
    ) = flow {
        try {
            emit(Response.Loading)
            auth.createUserWithEmailAndPassword(
                email,
                password
            )
                .addOnSuccessListener {
                    Log.d("TAG", "WelcomeScreen: Success")
                }
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val currentUser = auth.currentUser
                        val user = HashMap<String, Any>()
                        user["name"] = name
                        user["mobile_no"] = mobile_no
                        user["email"] = email
                        currentUser?.let { firebaseUser ->
                            userCollectionRef.document(firebaseUser.uid)
                                .set(user)
                                .addOnSuccessListener {
//                                    navController.navigate(Screen.MainScreen.route)
                                    Log.d(
                                        "TAG",
                                        "DocumentSnapshot successfully written!"
                                    )
                                }
                                .addOnFailureListener { e ->
                                    Log.w(
                                        "TAG",
                                        "Error writing document",
                                        e
                                    )
                                }
                        }
                        Log.d("TAG", "WelcomeScreen: Registration Success")
                    } else (Log.d("TAG", "Failed: ${task.exception}"))
                }
        } catch (e: Exception) {
            emit(Error(e.message ?: e.toString()))
        }
    }

    override fun getFirebaseAuthState() = callbackFlow {
        val authStateListener = FirebaseAuth.AuthStateListener { auth ->
            trySend(auth.currentUser == null)
        }
        auth.addAuthStateListener(authStateListener)
        awaitClose {
            auth.removeAuthStateListener(authStateListener)
        }
    }
}