package com.majority.assignment.ui.countries

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.majority.assignment.data.entities.countryInfo.CountryInfo
import com.majority.assignment.databinding.ItemCountryBinding


class CountriesAdapter(
    private val openCountryInfo: (
        binding: ItemCountryBinding,
        countryInfo: CountryInfo
    ) -> Unit
) :
    RecyclerView.Adapter<CountryViewHolder>(), Filterable {


    private val searchableList = ArrayList<CountryInfo>()
    private val originalList = ArrayList(searchableList)
    private var searchResultCallBack: ((isDataFound: Boolean) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<CountryInfo>?) {
        this.searchableList.clear()
        items?.let {
            this.searchableList.addAll(it)
            originalList.addAll(it)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding: ItemCountryBinding =
            ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(binding)
    }

    override fun getItemCount(): Int = searchableList.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(searchableList[position])

        holder.itemView.setOnClickListener {

            openCountryInfo.invoke(
                holder.view,
                searchableList[position]
            )


        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            private val filterResults = FilterResults()
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint.toString()
                searchableList.clear()
                if (constraint.isNullOrBlank()) {
                    searchableList.addAll(originalList)
                } else {
                    val searchResults =
                        originalList.filter {
                            it.name?.official?.lowercase()?.contains(charString.lowercase())!!
                        }
                    searchableList.addAll(searchResults)
                }
                return filterResults.also {
                    it.values = searchableList
                }

            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                // no need to use "results" filtered list provided by this method.
                searchResultCallBack?.invoke(searchableList.isNullOrEmpty())

                notifyDataSetChanged()

            }
        }
    }


    fun search(s: String?, onNothingFound: ((isDataFound: Boolean) -> Unit)?) {
        this.searchResultCallBack = onNothingFound
        filter.filter(s)
    }


}


class CountryViewHolder(
    val view: ItemCountryBinding
) : RecyclerView.ViewHolder(view.root) {

    fun bind(item: CountryInfo) {
        view.countryInfo = item
        view.executePendingBindings()

    }


}

