package sec03.brd02;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class BoardController
 */
// @WebServlet("/board/*")
public class BoardController extends HttpServlet {
	private static String ARTICLE_IMAGE_REPO = "C:\\board\\article_image";
	// 글에 첨부한 이미지 저장 위치를 상수로 선언합니다.
	
	BoardService boardService;
	ArticleVO articleVO;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		boardService = new BoardService();
		articleVO = new ArticleVO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = "";
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String action = request.getPathInfo();
		
		System.out.println("action:" + action);
		try {
			List<ArticleVO> articlesList = new ArrayList<ArticleVO>();
			if (action == null) {
				articlesList = boardService.listArticles();
				
				request.setAttribute("articlesList", articlesList);
				
				nextPage = "/board02/listArticles.jsp";
				
			} else if (action.equals("/listArticles.do")) {
				articlesList = boardService.listArticles();
				
				request.setAttribute("articlesList", articlesList);
				
				nextPage = "/board02/listArticles.jsp";
				
			} else if (action.equals("/articleForm.do")) { // action값 /articleForm.do로 요청시 글쓰기창이 나타난다.
				
				nextPage = "/board02/articleForm.jsp";
				
			} else if (action.equals("/addArticle.do")) { // /addArticle.do로 요청 시 새 글 추가 작업을 수행한다.
				Map<String, String> articleMap = upload(request, response);
				// 파일 업로드 기능을 사용하기 위해 upload()로 요청을 전달한다.
				
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String imageFileName = articleMap.get("imageFileName");
				// articleMap에 저장된 글 정보를 다시 가져온다.

				articleVO.setParentNO(0);
				// 새 글의 부모 글 번호를 0으로 설정한다.
				articleVO.setId("hong");
				// 새 글 작성자 ID를 hong으로 설정한다.
				articleVO.setTitle(title);
				articleVO.setContent(content);
				articleVO.setImageFileName(imageFileName);
				boardService.addArticle(articleVO);
				// 글쓰기창에서 입력한 정보를 ArticleVO 객체에 설정한 후 addArticle()로 전달합니다.
				
				nextPage = "/board/listArticles.do";
			}else {
				nextPage = "/board02/listArticles.jsp";
			}

			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> articleMap = new HashMap<String, String>();
		String encoding = "utf-8";
		
		File currentDirPath = new File(ARTICLE_IMAGE_REPO);
		// 글 이미지 저장 폴더에 대해 파일 객체를 생성한다.
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(currentDirPath);
		factory.setSizeThreshold(1024 * 1024);
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
			List items = upload.parseRequest(request);
			for (int i = 0; i < items.size(); i++) {
				FileItem fileItem = (FileItem) items.get(i);
				if (fileItem.isFormField()) {
					System.out.println(fileItem.getFieldName() + "=" + fileItem.getString(encoding));
					articleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
					// 파일 업로드로 같이 전송된 새 글 관련 매개변수를 Map에 (key, value)로 저장한 후 반환하고,
					// 새글과 관련된 title, content를 Map에 저장한다.
				} else {
					System.out.println("파라미터명:" + fileItem.getFieldName());
					System.out.println("파일명:" + fileItem.getName());
					System.out.println("파일크기:" + fileItem.getSize() + "bytes");
					
					articleMap.put(fileItem.getFieldName(), fileItem.getName());
					// 업로드된 파일의 파일 이름을 Map에 ("imageFileName","업로드파일이름")로 저장한다.
					
					if (fileItem.getSize() > 0) {
						int idx = fileItem.getName().lastIndexOf("\\");
						if (idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");
						}

						String fileName = fileItem.getName().substring(idx + 1);
						System.out.println("파일명:" + fileName);
						articleMap.put(fileItem.getFieldName(), fileName);  //익스플로러에서 업로드 파일의 경로 제거 후 map에 파일명 저장
						File uploadFile = new File(currentDirPath + "\\" + fileName);
						fileItem.write(uploadFile);

					} // end if
					// 업로드한 파일이 존재하는 경우 업로드한 파일의 파일 이름으로 저장소에 업로드 한다.
					
				} // end if
			} // end for
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleMap;
	}

}
