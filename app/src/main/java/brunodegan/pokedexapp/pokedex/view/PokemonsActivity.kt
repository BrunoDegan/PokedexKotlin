package brunodegan.pokedexapp.pokedex.view

import android.graphics.Typeface
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.content.res.ResourcesCompat
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.StaggeredGridLayoutManager
import android.text.Spannable
import android.text.SpannableString
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import brunodegan.pokedexapp.pokedex.R
import brunodegan.pokedexapp.pokedex.data.PokemonMock
import brunodegan.pokedexapp.pokedex.utils.CustomTypefaceSpan
import kotlinx.android.synthetic.main.activity_pokemon.*
import kotlinx.android.synthetic.main.app_bar_pokemon.*

class PokemonsActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_pokemon)
		setSupportActionBar(app_toolbar)
		customFontNavigationViewMenu()
		
		val toggle = ActionBarDrawerToggle(
				this, drawer_layout, app_toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
		
		drawer_layout.addDrawerListener(toggle)
		toggle.syncState()
		nav_view.setNavigationItemSelectedListener(this)
		initRecycler()
	}
	
	fun initRecycler() {
		rv_pokemons.setHasFixedSize(true)
		
		val mLayoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
		rv_pokemons.layoutManager = mLayoutManager
		
		rv_pokemons.adapter = PokemonAdapter( applicationContext, PokemonMock.getPokemons())
	}
	
	override fun onBackPressed() {
		if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
			drawer_layout.closeDrawer(GravityCompat.START)
		} else {
			super.onBackPressed()
		}
	}
	
	override fun onCreateOptionsMenu(menu: Menu): Boolean {
		// Inflate the menu; this adds items to the action bar if it is present.
		menuInflater.inflate(R.menu.pokemons, menu)
		return true
	}
	
	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		when (item.itemId) {
			R.id.action_settings -> return true
			else -> return super.onOptionsItemSelected(item)
		}
	}
	
	override fun onNavigationItemSelected(item: MenuItem): Boolean {
		// Handle navigation view item clicks here.
		drawer_layout.closeDrawer(GravityCompat.START)
		return true
	}
	
	//gambiarra para setar typeface custom na navigation drawer e sub menus
	private fun setCustomFontMenuItem(menuItem: MenuItem, typeface : Typeface) {
//		val font = ResourcesCompat.getFont(this, R.font.indie_flower)
//		textItem.setSpan(
//				CustomTypefaceSpan(font ?: Typeface.DEFAULT),
//				0,
//				textItem.length,
//				Spannable.SPAN_INCLUSIVE_INCLUSIVE )
		
		val textItem = SpannableString(menuItem.title)
		
		textItem.setSpan(
				CustomTypefaceSpan(typeface),
				0,
				textItem.length,
				Spannable.SPAN_INCLUSIVE_INCLUSIVE )
		
		menuItem.title = textItem
	}
	
	private fun customFontNavigationViewMenu() {
		
		val pokemonSolidFont = ResourcesCompat.getFont(this, R.font.pokemon_solid)
		
		val sansSerifFontStyle = Typeface.create("sans-serif", Typeface.NORMAL)
		
		val menu = nav_view.menu
		
		for (i in 0..menu.size() - 1 ) {
			
			val menuItem = menu.getItem(i)
			setCustomFontMenuItem(menuItem, sansSerifFontStyle)
			
			if (menuItem.subMenu != null) {
				setCustomFontMenuItem(menuItem, pokemonSolidFont ?: sansSerifFontStyle)
				val subMenu = menuItem.subMenu
				
				for (item in 0..subMenu.size()-1) {
					val subMenuItem = subMenu.getItem(item)
					setCustomFontMenuItem(subMenuItem, sansSerifFontStyle)
				}
				
			}
		}
	}
}
