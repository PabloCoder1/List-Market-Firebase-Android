package br.unisanta.androidloginfirebase

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.unisanta.androidloginfirebase.databinding.ActivityListaBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Lista : AppCompatActivity() {

    private lateinit var binding: ActivityListaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = Firebase.firestore

        binding.btnGetAllProd.setOnClickListener {
            db.collection("Produtos")
                .get()
                .addOnSuccessListener { result ->
                    if (result.isEmpty) {
                        Toast.makeText(this, "Nenhum produto encontrado", Toast.LENGTH_SHORT).show()
                    } else {
                        val produtos = mutableListOf<Produto>()

                        for (document in result) {
                            val nome = document.getString("Nome")
                            val categoria = document.getString("Categoria")
                            val preco = document.getLong("Preço")?.toInt()

                            val produto = Produto(nome, categoria, preco)
                            produtos.add(produto)
                        }

                        if (produtos.isEmpty()) {
                            Toast.makeText(this, "Nenhum produto encontrado", Toast.LENGTH_SHORT).show()
                        } else {
                            val recyclerView: RecyclerView = binding.recyclerView
                            recyclerView.layoutManager = LinearLayoutManager(this)
                            val adapter = ProdutoAdapter(produtos)
                            recyclerView.adapter = adapter
                        }
                    }
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(this, "Erro ao carregar produtos: ${exception.message}", Toast.LENGTH_SHORT).show()
                }
        }


    }
}