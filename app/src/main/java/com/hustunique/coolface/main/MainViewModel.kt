package com.hustunique.coolface.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.bmob.v3.BmobUser
import com.hustunique.coolface.bean.Post
import com.hustunique.coolface.bean.Resource
import com.hustunique.coolface.bean.User
import com.hustunique.coolface.model.repo.PictureRepo
import com.hustunique.coolface.model.repo.PostRepo

class MainViewModel : ViewModel() {
    val user = MutableLiveData<User>()
    private lateinit var postRepo: PostRepo

    val postsData: MutableLiveData<Resource<List<Post>>> = MutableLiveData()

    fun init() {
        postRepo = PostRepo.getInstance()
        user.postValue(BmobUser.getCurrentUser(User::class.java))
        getPosts()
    }

    private fun getPosts() {
        postRepo.getPosts(postsData)
    }

    fun like(positon: Int, onError: ((String) -> Unit)) {
        postsData.value?.data?.let {
            postRepo.like(it[positon].objectId!!, null, onError)
        }
    }

    fun unLike(positon: Int, onError: ((String) -> Unit)) {
        postsData.value?.data?.let {
            postRepo.unLike(it[positon].objectId!!, null, onError)
        }
    }

    fun getPictureFile() = PictureRepo.getInstance().getNewFile()
}