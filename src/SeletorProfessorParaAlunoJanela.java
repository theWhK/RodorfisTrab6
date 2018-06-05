import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class SeletorProfessorParaAlunoJanela extends JFrame {
	
	public JPanel titulo;
	public JPanel botoes;
	public JLabel tituloTexto;
	public JComboBox<Professor> listagem;
	public JButton botaoNovo;
	
	public List<Professor> listagemProfs;
	public MyListModel<Professor> listagemModel;
	
	public SeletorProfessorParaAlunoJanela() {
		super("Listagem de Alunos");
		
		listagemProfs = CRUDFaculdade.hi().getProfessor();
		listagemModel = new MyListModel<>();
		
		for(int i=0; i<listagemProfs.size(); i++){
			listagemModel.add(listagemProfs.get(i));
		}
		
		// Inicia os elementos
		titulo = new JPanel();
		tituloTexto = new JLabel("Listagem de Alunos");
		listagem = new JComboBox(listagemModel);
		botaoNovo = new JButton("Ok");
		botoes = new JPanel();
		
		// Adiciona os elementos na interface
		titulo.add(tituloTexto);
		add(titulo, BorderLayout.NORTH);
		add(listagem);
		botoes.add(botaoNovo);
		botoes.add(botaoMaisNovo);
		botoes.add(botaoNovissimo);
		add(botoes, BorderLayout.SOUTH);
		
		// Listeners
			// Criar
			botaoNovo.addActionListener((e)->{
				String nome = JOptionPane.showInputDialog(this, "Nome: ");
				String cpf = JOptionPane.showInputDialog(this, "CPF: ");
				String rg = JOptionPane.showInputDialog(this, "RG: ");
				String ra = JOptionPane.showInputDialog(this, "RA:" );
				String dataDeMatricula = JOptionPane.showInputDialog(this, "Data de matricula: ");
				Aluno a = new Aluno();
				a.setNome(nome);
				a.setCpf(cpf);
				a.setRg(rg);
				a.setRa(ra);
				a.setCpf(cpf);
				a.setDataDeMatricula(dataDeMatricula);
				CRUDFaculdade.hi().addAluno(a);
				listagemModel.add(a);
			});
			// Atualizar
			botaoMaisNovo.addActionListener((e)->{
				try {
					Aluno a = listagem.getSelectedValue();
					String nome = JOptionPane.showInputDialog(this, "Nome: ", a.getNome());
					String cpf = JOptionPane.showInputDialog(this, "CPF: ", a.getCpf());
					String rg = JOptionPane.showInputDialog(this, "RG: ", a.getRg());
					String ra = JOptionPane.showInputDialog(this, "Ra:" ,a.getRa());
					String dataDeMatricula = JOptionPane.showInputDialog(this, "Data de matricula: ", a.getDataDeMatricula());
					a.setNome(nome);
					a.setCpf(cpf);
					a.setRg(rg);
					a.setRa(ra);
					a.setCpf(cpf);
					a.setDataDeMatricula(dataDeMatricula);
					listagem.repaint();
				}
				catch (NullPointerException oCaraNaoSelecionouNadaMeuDeus) {
					JOptionPane.showMessageDialog(
							this, 
							"Selecione um elemento na lista.", 
							"Erro", 
							JOptionPane.ERROR_MESSAGE);
				}
				catch (Exception vishNemSei) {
					JOptionPane.showMessageDialog(
							this, 
							"Erro n�o previsto.", 
							"Erro", 
							JOptionPane.ERROR_MESSAGE);
				}
				
			});
			// Remover
			botaoNovissimo.addActionListener((e)->{
				try {
					int index = listagem.getSelectedIndex();
					CRUDFaculdade.hi().removeAlunoAt(index);
					listagemModel.remove(index);
				}
				catch (NullPointerException oCaraNaoSelecionouNadaMeuDeus) {
					JOptionPane.showMessageDialog(
							this, 
							"Selecione um elemento na lista.", 
							"Erro", 
							JOptionPane.ERROR_MESSAGE);
				}
				catch (Exception vishNemSei) {
					JOptionPane.showMessageDialog(
							this, 
							"Erro n�o previsto.", 
							"Erro", 
							JOptionPane.ERROR_MESSAGE);
				}
			});
		
		// Mostra a interface
		setSize(500,500);
		setVisible(true);
	}
}