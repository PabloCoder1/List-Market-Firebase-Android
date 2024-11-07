package br.unisanta.androidloginfirebase

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.unisanta.androidloginfirebase.databinding.ActivityProdutosBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Produtos : AppCompatActivity() {

    private lateinit var binding: ActivityProdutosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProdutosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = Firebase.firestore

        binding.btnSalvarProd.setOnClickListener {

            val product = binding.edtPrduct.text.toString()
            val category = binding.edtCategory.text.toString()
            val price = binding.edtPreO.text.toString().toInt()
            val client = hashMapOf(
                "Nome" to product,
                "Categoria" to category,
                "Preço" to price
            )
            db.collection("Produtos")
                .add(client)
                .addOnSuccessListener {
                    Toast.makeText(this, "Boa mlk", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Mandou mal mlk", Toast.LENGTH_SHORT).show()
                }
        }
        binding.btnMudar.setOnClickListener {
            val intent = Intent(this, Lista::class.java)
            startActivity(intent)
        }

    }
}