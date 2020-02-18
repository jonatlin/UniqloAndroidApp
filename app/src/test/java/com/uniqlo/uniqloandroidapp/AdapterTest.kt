package com.uniqlo.uniqloandroidapp

import com.uniqlo.uniqloandroidapp.adapter.PreviewItemAdapter
import com.uniqlo.uniqloandroidapp.model.Item
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class AdapterTest {

    private lateinit var previewItemAdapter: PreviewItemAdapter

    @Before
    fun setup() {

        previewItemAdapter = PreviewItemAdapter()

    }

    @Test
    fun testList() {

        previewItemAdapter.submitList( mutableListOf(Item(), Item("ID", "Name")) )
        assertEquals( 2, previewItemAdapter.itemCount )
    }
}