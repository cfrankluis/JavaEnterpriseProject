package socialmediaplatform.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name = "Security_Question_answer")
public class SecurityQuestionAnswer {

	@Id
	@Column(name = "answer_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int answerId;
	
	@Column(name = "answer", nullable=false)
	private String answer;
	
	@ManyToOne
	@Column(name = "user_id")
	private User user;
	
	@ManyToOne
	@Column(name="question_id")
	private SecurityQuestion securityquestion;
}
