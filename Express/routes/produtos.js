import express from "express";

const router = express.Router();

export default (supabase) => {
  /*
   Adicionar um novo produto
  */
  router.post("/", async (req, res) => {
    const { nome, preco } = req.body;
    const { data, error } = await supabase
      .from("tb_produtos")
      .insert([{ nome, preco }]);

    if (error) {
      return res.status(400).json({ erro: error.message });
    }
    return res
      .status(201)
      .json({ mensagem: "Produto adicionado com sucesso!" });
  });

  /*
  Listar os produtos
  */
  router.get("/", async (req, res) => {
    const { data, error } = await supabase.from("tb_produtos").select("*");
    if (error) return res.status(400).json({ erro: error.message });
    return res.json(data);
  });

  /*
  Retornar produtos por ID
  */
  router.get("/:id", async (req, res) => {
    const { id } = req.params;
    const { data, error } = await supabase
      .from("tb_produtos")
      .select("*")
      .eq("id", id)
      .single();
    if (error) return res.status(404).json({ erro: error.message });
    return res.json(data);
  });
  
  /*
   Deletar um produto pelo ID
  */
  router.delete("/:id", async(req,res) =>{
      const {id} = req.params;
      const {data, error} = await supabase.from('tb_produtos').delete().eq('id',id);

      if (error) return res.status(400).json({erro: error.message});

      return res.status(200).json({mensagem: 'Produto apagado com sucesso!'});
  });    
  

  return router;
};
