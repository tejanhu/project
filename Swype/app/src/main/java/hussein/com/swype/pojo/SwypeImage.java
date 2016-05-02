package hussein.com.swype.pojo;

import android.net.Uri;

/**
 *
 */
public class SwypeImage {

    //Uri of the image
    Uri imageUri;

    //True means liked, false is unliked
    Boolean status;

    public SwypeImage(Uri imageUri, Boolean status) {
        this.imageUri = imageUri;
        this.status = status;
    }

    public SwypeImage(Uri imageUri) {
        this.imageUri = imageUri;
        this.status = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SwypeImage that = (SwypeImage) o;

        if (imageUri != null ? !imageUri.equals(that.imageUri) : that.imageUri != null)
            return false;
        return status != null ? status.equals(that.status) : that.status == null;

    }

    @Override
    public int hashCode() {
        int result = imageUri != null ? imageUri.hashCode() : 0;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SwypeImage{");
        sb.append("imageUri=").append(imageUri);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
