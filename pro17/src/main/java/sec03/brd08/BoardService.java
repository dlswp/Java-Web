package sec03.brd08;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardService {

	BoardDAO boardDAO;
	
	public BoardService() {
		boardDAO = new BoardDAO();
		// 생성자 호출 시 BoardDAO 객체를 생성한다.
	}
	
	public Map listArticles(Map<String, Integer> pagingMap) {
		Map articlesMap = new HashMap();
		List<ArticleVO> articlesList = boardDAO.selectAllArticles(pagingMap);
		// 전달된 pagingMap을 사용해 글 목록을 조회한다.
		
		int totArticles = boardDAO.selectTotArticles();
		// 테이블에 존재하는 전체 글 수를 조회한다.
		
		articlesMap.put("articlesList", articlesList);
		// 조회된 글 목록을 ArrayList에 저장한 후 다시 articlesMap에 저장한다.
		
		articlesMap.put("totArticles", totArticles);
		// 전체 글 수를 articlesMap에 저장한다.
		
		return articlesMap;
	}
	
	public List<ArticleVO> listArticles(){
		List<ArticleVO> articlesList = boardDAO.selectAllArticles();
		return articlesList;
	}
	
	public int addArticle(ArticleVO article) {
		return boardDAO.insertNewArticle(article);
	}
	
	public ArticleVO viewArticle(int articleNO) {
		ArticleVO article = null;
		article = boardDAO.selectArticle(articleNO);
		return article;
	}
	
	public void modArticle(ArticleVO article) {
		boardDAO.updateArticle(article);
	}
	
	public List<Integer> removeArticle(int articleNO){
		List<Integer> articleNOList = boardDAO.selectRemovedArticles(articleNO);
		boardDAO.deleteArticle(articleNO);
		return articleNOList;
	}
	
	public int addReply(ArticleVO article) {
		return boardDAO.insertNewArticle(article);
		// 새 글 추가 시 사용한 insertNewArticle() 메서드를 이용해 답글을 추가한다.
	}

}
