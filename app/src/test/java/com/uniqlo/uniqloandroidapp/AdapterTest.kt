package com.uniqlo.uniqloandroidapp

import com.uniqlo.uniqloandroidapp.adapter.ItemPreviewAdapter
import com.uniqlo.uniqloandroidapp.data.Item
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class AdapterTest {

    private lateinit var itemPreviewAdapter: ItemPreviewAdapter

    @Before
    fun setup() {

        itemPreviewAdapter = ItemPreviewAdapter()

    }

    @Test
    fun testList() {

        itemPreviewAdapter.submitList( mutableListOf(Item(), Item("ID", "Name")) )
        assertEquals( 2, itemPreviewAdapter.itemCount )
    }
}