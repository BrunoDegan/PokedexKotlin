package brunodegan.pokedexapp.pokedex.view

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import brunodegan.pokedexapp.pokedex.R
import brunodegan.pokedexapp.pokedex.domain.Pokemon
import org.jetbrains.anko.startActivity


/**
 * Created by brunodegan on 9/1/17.
 */
class PokemonAdapter(private  val context : Context,
                     private val pokemonList : List<Pokemon>):
                     RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {
	
	override fun getItemCount(): Int {
		return pokemonList.size
	}
	
	override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
		holder?.setData(pokemonList[position])
	}
	
	override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
		val layoutInflated = LayoutInflater.from(context).
				inflate(R.layout.pokemon_item, parent, false)
		
		return ViewHolder(layoutInflated)
	}
	
	
	inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
		var pokemonImg : ImageView
		var pokemonName : TextView
		var pokemonNumber : TextView
		var pokemonType : TextView
		
		init {
			itemView.setOnClickListener(this)
			pokemonImg = itemView.findViewById(R.id.iv_pokemon_image)
			pokemonName = itemView.findViewById(R.id.tv_pokemon_name)
			pokemonNumber = itemView.findViewById(R.id.tv_pokemon_number)
			pokemonType = itemView.findViewById(R.id.tv_pokemon_type)
		}
		
		fun setData(pokemon : Pokemon) {
			pokemonImg.setImageResource(pokemon.imagemRes)
			pokemonNumber.text = pokemon.number.toString().padStart(3,'0')
			pokemonName.text = pokemon.name
			pokemonType.text = pokemon.getFormattedType()
		}
		
		override fun onClick(clickedView: View?) {
			val intent = Intent(context, PokemonDetailsActivity::class.java)
			intent.putExtra(Pokemon.KEY, pokemonList[adapterPosition])
			context.startActivity(intent)
		}
	}
	
}