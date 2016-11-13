package game.server;

public class GameServerLOL {
	
	public static void main(String args) {
		Server server = new Server(2);
		server.run();
	}
	
//	Server serverT;
//	public GameServer(GameStateManager gsm, int playerCount) {
//		super(gsm);
//		this.serverT = new Server(playerCount);
//		// TODO Auto-generated constructor stub
//	}
//
//	@Override
//	public void onEnter() {
//		// TODO Auto-generated method stub
//		serverT.run();
//	}
//
//	@Override
//	public void update(double delta) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void draw(Graphics2D g) {
//		// TODO Auto-generated method stub
//		g.clearRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
//		g.drawString("Welcome Server", 50, 10);
//		for(int i = 0; i < serverT.players.length;i++)
//		{
//			if(serverT.players[i]!=null)
//			{
//				g.drawString(serverT.players[i].print(), 100, 10+(i+1)*20);
//				
//			}
//		}
//	}
//
//	@Override
//	public void onExit() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void keyTyped(int keyCode) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void keyPressed(int keyCode) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void keyReleased(int keyCode) {
//		// TODO Auto-generated method stub
//		
//	}
}
