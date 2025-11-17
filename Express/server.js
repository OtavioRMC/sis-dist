import express from "express";
import dotenv from "dotenv";
import { createClient } from "@supabase/supabase-js";
import produtosRoutes from "./routes/produtos.js";
dotenv.config();

const app = express();
const port = 3000;

const supabase = createClient(
  process.env.SUPABASE_URL,
  process.env.SUPABASE_KEY,
);

app.use(express.json());
app.use("/produtos", produtosRoutes(supabase));

app.listen(port, () => {
  console.log("Servidor em execução!");
});
