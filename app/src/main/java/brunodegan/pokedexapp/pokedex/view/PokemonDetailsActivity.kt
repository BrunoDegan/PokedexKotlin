package brunodegan.pokedexapp.pokedex.view

import android.graphics.Typeface
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import brunodegan.pokedexapp.pokedex.R
import brunodegan.pokedexapp.pokedex.domain.Pokemon
import kotlinx.android.synthetic.main.activity_pokemon_details.*
import kotlinx.android.synthetic.main.app_bar_pokemon.*
import kotlinx.android.synthetic.main.content_details.*

/**
 * Created by brunodegan on 9/1/17.
 */
class PokemonDetailsActivity : AppCompatActivity() {

	var pokemon : Pokemon ?= null
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_pokemon_details)
		
		setSupportActionBar(pokemon_details_toolbar)
		
		supportActionBar?.setDisplayHomeAsUpEnabled(true)
		supportActionBar?.setDisplayHomeAsUpEnabled(true)
		
		share_floating_action_button.setOnClickListener { view ->
			customToast()
		}
		
		ct_layout.setCollapsedTitleTypeface(ResourcesCompat.getFont(this,
				R.font.indie_flower))
		ct_layout.setExpandedTitleTypeface(ResourcesCompat.getFont(this,
				R.font.pokemon_solid))
		
		pokemon = intent.getParcelableExtra(Pokemon.KEY)
		
		iv_header.setImageResource(pokemon?.imagemRes?:0)
		tv_descricao.text = pokemon?.description
		tv_altura.text = pokemon?.height
		
		//metodo pra completar com zeros a string selecionada
		
		tv_numero.text = pokemon?.number.toString().padStart(3, '0')
		tv_peso.text = pokemon?.weight
		tv_sexo.text = pokemon?.gender
		tv_categoria.text = pokemon?.category
		tv_habilidades.text = pokemon?.getFormatedHabilities()
		tv_tipo.text = pokemon?.getFormattedType()
		tv_fraquezas.text = pokemon?.getFormattedWeekness()
		
		
	}
	
	override fun onResume() {
		super.onResume()
		pokemon_details_toolbar?.title = pokemon?.name
	}
	
	override fun onOptionsItemSelected(item: MenuItem?): Boolean {
		val itemId = item?.itemId
		if(itemId == android.R.id.home) {
			finish()
			return true
		}
		return super.onOptionsItemSelected(item)
	}
	
	fun customToast() {
		val layoutInflater = LayoutInflater.from(applicationContext)
		
		val toastLayout = layoutInflater.inflate(R.layout.toast_custom_layout, findViewById(R.id.share_toast))
		
		val toastTvInfo = toastLayout.findViewById<TextView>(R.id.custom_toast_tv_info)
		
		toastTvInfo.text = "Ainda falta implementar esta parte"
		toastTvInfo.typeface = Typeface.create("sans-serif", Typeface.NORMAL)
		
		val toast = Toast(applicationContext)
		toast.duration = Toast.LENGTH_SHORT
		toast.view = toastLayout
		toast.show()
	}
}