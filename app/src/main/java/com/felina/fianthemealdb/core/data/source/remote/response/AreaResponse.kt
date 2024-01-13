package com.felina.fianthemealdb.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class AreaResponse(

	@field:SerializedName("meals")
	val meals: List<AreaItem>
)

data class AreaItem(

	@field:SerializedName("strArea")
	val strArea: String
)
