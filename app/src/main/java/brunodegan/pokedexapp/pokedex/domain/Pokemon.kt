package brunodegan.pokedexapp.pokedex.domain

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by brunodegan on 9/1/17.
 */


class Pokemon(
		val number: Int,
		val name: String,
		val imagemRes: Int,
		val description: String,
		val height: String,
		val weight: String,
		val gender: String,
		val category: String,
		val type: Array<String>,
		val habilities: Array<String>,
		val weekness: Array<String>) : Parcelable {
	
	fun getFormattedType(): String {
		return type.joinToString(", ")
	}
	
	fun getFormatedHabilities(): String {
		return habilities.joinToString(", ")
	}
	
	fun getFormattedWeekness(): String {
		return weekness.joinToString(", ")
	}
	
	constructor(source: Parcel) : this(
			source.readInt(),
			source.readString(),
			source.readInt(),
			source.readString(),
			source.readString(),
			source.readString(),
			source.readString(),
			source.readString(),
			source.createStringArray(),
			source.createStringArray(),
			source.createStringArray()
	)
	
	override fun describeContents() = 0
	
	override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
		writeInt(number)
		writeString(name)
		writeInt(imagemRes)
		writeString(description)
		writeString(height)
		writeString(weight)
		writeString(gender)
		writeString(category)
		writeStringArray(type)
		writeStringArray(habilities)
		writeStringArray(weekness)
	}
	
	companion object {
		@JvmField
		val KEY = "pokemon"
		
		fun createFromParcel(parcel: Parcel): Pokemon {
			return Pokemon(parcel)
		}
		
		fun newArray(size: Int): Array<Pokemon?> {
			return arrayOfNulls(size)
		}
		
		@JvmField
		val CREATOR: Parcelable.Creator<Pokemon> = object : Parcelable.Creator<Pokemon> {
			override fun createFromParcel(source: Parcel): Pokemon = Pokemon(source)
			override fun newArray(size: Int): Array<Pokemon?> = arrayOfNulls(size)
		}
	}
}